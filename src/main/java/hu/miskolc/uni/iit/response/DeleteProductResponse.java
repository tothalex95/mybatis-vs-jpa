package hu.miskolc.uni.iit.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@ToString
public class DeleteProductResponse {

	private long elapsedTime;

	private boolean succeeded;

}
