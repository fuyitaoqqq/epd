package cn.animalcity.apidocument.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: fuyitao
 * @date: 2019/04/04
 */

@Data
@NoArgsConstructor
public abstract class AbstractProjectParent {

    private String name;

    private String uri;

}
