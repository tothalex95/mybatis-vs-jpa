package hu.miskolc.uni.iit.request;

import hu.miskolc.uni.iit.model.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@ToString
public class CreateProductRequest {

	private ORMType ormType;

	private Product product;

}
