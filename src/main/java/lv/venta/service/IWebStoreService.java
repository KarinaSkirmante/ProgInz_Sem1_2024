package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Product;
import lv.venta.model.dto.PurchaseDTO;

public interface IWebStoreService {
	ArrayList<Product> buy(PurchaseDTO purchase) throws Exception;

}
