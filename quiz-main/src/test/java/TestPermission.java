import com.quiz.Main;
import com.quiz.entity.Permission;
import com.quiz.enumerate.PermissionEnum;
import com.quiz.service.IPermissionService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <p>
 * TestPermission
 * </p>
 *
 * @author XGeorge
 * @since 2024/8/6 上午10:08
 */
@SpringBootTest(classes = Main.class)
public class TestPermission {


    @Autowired
    private IPermissionService permissionService;


    /**
     * 初始化权限
     */
    @Test
    public void test() {
        val permissions = Arrays.stream(PermissionEnum.values())
                .map(permissionEnum -> Permission.builder().permissionId(permissionEnum.ordinal() + 1).permissionName(permissionEnum.name()).describe(permissionEnum.getDescribe()).build())
                .collect(Collectors.toList());
        permissionService.saveOrUpdateBatch(permissions);
    }
}
