package org.project.myapp.Services;

import lombok.RequiredArgsConstructor;
import org.project.myapp.Exception.DataNotFoundException;
import org.project.myapp.Repositoris.CategoryRepositoriy;
import org.project.myapp.Repositoris.ProductImageRepository;
import org.project.myapp.Repositoris.ProductRepository;
import org.project.myapp.dtos.ProductDTO;
import org.project.myapp.dtos.ProductImageDTO;
import org.project.myapp.models.Category;
import org.project.myapp.models.Product;
import org.project.myapp.models.ProductImage;
import org.project.myapp.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Optional;

@RequiredArgsConstructor
@Service

public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepositoriy categoryRepositoriy;
    private final ProductImageRepository productImageRepository;


    @Override
    public Product createProduct(ProductDTO productDTO) throws Exception {
        Category existingCategory = categoryRepositoriy
                .findById(productDTO.getCategoryId())
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "can't found category with id: " + productDTO.getCategoryId()));
        Product newProduct = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .thumbnail(productDTO.getThumbnail())
                .description(productDTO.getDescription())
                .category(existingCategory)
                .build();
        return productRepository.save(newProduct);
    }

    @Override
    public Product getProductById(long productId) throws Exception {
        return productRepository.findById(productId)
                .orElseThrow(
                        () -> new DataNotFoundException(
                                "Can't found product with id = " + productId));
    }

    @Override
    public Page<ProductResponse> getAllProducts(PageRequest pageRequest) {
        // lay dnah sach san pham theo trang va gioi han
        return productRepository.findAll(pageRequest).map(ProductResponse::fromProduct);
    }

    @Override
    public Product updateProduct(long id,
                                 ProductDTO productDTO
    ) throws Exception {
        Product existingProduct = getProductById(id);
        if (existingProduct != null) {
            //copy cac thuoc tinh tu DTO sang cho product
            Category existingCategory = categoryRepositoriy
                    .findById(productDTO.getCategoryId())
                    .orElseThrow(() ->
                            new DataNotFoundException(
                                    "can't found category with id: " + productDTO.getCategoryId()));
            existingProduct.setName(productDTO.getName());
            existingProduct.setCategory(existingCategory);
            existingProduct.setPrice(productDTO.getPrice());
            existingProduct.setDescription(productDTO.getDescription());
            existingProduct.setThumbnail(productDTO.getThumbnail());
            return productRepository.save(existingProduct);

        }
        return null;
    }

    @Override
    public void deleteProduct(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        optionalProduct.ifPresent(productRepository::delete);

    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }
    @Override
    public ProductImage createProductImage(
            Long productId,
            ProductImageDTO productImageDTO
    ) throws Exception {
        //copy cac thuoc tinh tu DTO sang cho product
        Product existingProduct = productRepository
                .findById(productId)
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "can't found category with id: " + productImageDTO.getProductId()));
        ProductImage newProductImage = ProductImage.builder()
                .product(existingProduct)
                .imageUrl(productImageDTO.getImageUrl())
                .build();

        //khong cho insert qua 5 anh cho 1 san pham
        int size = productImageRepository.findByProductId(productId).size();
        if (size>=ProductImage.MAXIMUM_IMAGES_PER_PRODUCT){
            throw new InvalidParameterException("You can only upload maximum"
                    + ProductImage.MAXIMUM_IMAGES_PER_PRODUCT
                    +" images");
        }
         return productImageRepository.save(newProductImage);

    }
}
