package hu.miskolc.uni.iit.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@ToString
public class SaveProductResponse {

	private long elapsedTime;

	private Integer id;

}
