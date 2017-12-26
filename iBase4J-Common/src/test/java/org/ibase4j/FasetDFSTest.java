package org.ibase4j;

import top.ibase4j.core.util.UploadUtil;
import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
public class FasetDFSTest {
    @Test
    public void main() {
        System.out.println(UploadUtil.remove2DFS(null, null, "D:\\xsit\\更新价格js.txt"));
    }

}
