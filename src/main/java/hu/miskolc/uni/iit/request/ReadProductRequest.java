package hu.miskolc.uni.iit.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@ToString
public class ReadProductRequest {

	private ORMType ormType;

	private Integer id;

}
