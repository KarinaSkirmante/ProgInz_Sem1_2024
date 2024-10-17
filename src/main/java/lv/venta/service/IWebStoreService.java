package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Product;
import lv.venta.model.dto.ProductDTOForBuy;

public interface IWebStoreService {
	ArrayList<Product> buy(ArrayList<ProductDTOForBuy> products) throws Exception;

}
