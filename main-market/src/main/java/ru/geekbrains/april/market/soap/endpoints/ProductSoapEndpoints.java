package ru.geekbrains.april.market.soap.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.april.market.models.Product;
import ru.geekbrains.april.market.soap.productSoap.*;

@Endpoint
@RequiredArgsConstructor
public class ProductSoapEndpoints {
//    private static final String NAMESPACE_URI = "http://www.geekbrains.ru/spring/ws/products";
//    private final ProductService productService;
//
//    /*
//
//    Пример для getProductSoapByIdRequest:
//
//       <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.geekbrains.ru/spring/ws/products">
//            <soapenv:Header/>
//            <soapenv:Body>
//                <f:getProductSoapByIdRequest>
//                    <f:id>1</f:id>
//                </f:getProductSoapByIdRequest>
//            </soapenv:Body>
//        </soapenv:Envelope>
//
//     */
//
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductSoapByIdRequest")
//    @ResponsePayload
//    public GetProductSoapByIdResponse getProductSoapByIdResponse(@RequestPayload GetProductSoapByIdRequest request) {
//        GetProductSoapByIdResponse response = new GetProductSoapByIdResponse();
//        Product product = productService.findById(request.getId()).get();
//        ProductSoap productSoap = new ProductSoap();
//        productSoap.setId(product.getId());
//        productSoap.setTitle(product.getTitle());
//        productSoap.setPrice(product.getPrice());
//        productSoap.setCategoryTitle(product.getCategory().getTitle());
//        productSoap.setCreatedAt(product.getCreatedAt().toString());
//        productSoap.setUpdatedAt(product.getUpdatedAt().toString());
//        response.setProductSoap(productSoap);
//        return response;
//    }
//
//    /*
//
//    Пример для getAllProductsSoapRequest:
//
//       <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.geekbrains.ru/spring/ws/products">
//            <soapenv:Header/>
//            <soapenv:Body>
//                <f:getAllProductsSoapRequest/>
//            </soapenv:Body>
//        </soapenv:Envelope>
//
//     */
//
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsSoapRequest")
//    @ResponsePayload
//    public GetAllProductsSoapResponse getAllStudents(@RequestPayload GetAllProductsSoapRequest request) {
//        GetAllProductsSoapResponse response = new GetAllProductsSoapResponse();
//        productService.getAllProducts().forEach(response.getProducts()::add);
//        return response;
//    }

}
