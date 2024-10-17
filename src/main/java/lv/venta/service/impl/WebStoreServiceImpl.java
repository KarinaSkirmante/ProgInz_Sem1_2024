package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.model.Purchase;
import lv.venta.model.dto.ProductDTOForBuy;
import lv.venta.model.dto.PurchaseDTO;
import lv.venta.repo.IProductRepo;
import lv.venta.repo.IPurchaseRepo;
import lv.venta.service.IWebStoreService;

@Service
public class WebStoreServiceImpl implements IWebStoreService {

	@Autowired
	private IProductRepo prodRepo;
	
	@Autowired
	private IPurchaseRepo purchaseRepo;
	
	
	@Override
	public ArrayList<Product> buy(PurchaseDTO purchase) throws Exception {
		ArrayList<ProductDTOForBuy> products = purchase.getProducts();
		float total = 0;
		if(products != null && !products.isEmpty())
		{
			ArrayList<Product> result = new ArrayList<>();
			for(ProductDTOForBuy tempP: products) {
				if(prodRepo.existsById(tempP.getId())) {
					Product productFromDB = prodRepo.findById(tempP.getId()).get();
					if(productFromDB.getQuantity() >= tempP.getQuantity()) {
						productFromDB.setQuantity(productFromDB.getQuantity()-tempP.getQuantity());
						prodRepo.save(productFromDB);
						result.add(productFromDB);
						total += productFromDB.getPrice()*tempP.getQuantity();
					}
					else
					{
						throw new Exception(productFromDB.getTitle() + " not so many in store");
					}
					
				}
				else
				{
					throw new Exception("Problems with product id: " + tempP.getId());
				}
			}
			
			Purchase purchaseForDB = new Purchase(purchase.getCustomerName(), purchase.getCustomerSurname(), total);
			purchaseRepo.save(purchaseForDB);
			
			
			return result;
			
		}
		else
		{
			throw new Exception("problem with product list");
		}
	}

}
