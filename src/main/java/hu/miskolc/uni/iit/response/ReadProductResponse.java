package hu.miskolc.uni.iit.response;

import java.util.ArrayList;
import java.util.List;

import hu.miskolc.uni.iit.model.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@ToString
public class ReadProductResponse {

	private long elapsedTime;

	private List<Product> products;

	public ReadProductResponse() {
		products = new ArrayList<>();
	}

}
