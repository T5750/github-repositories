package cn.ictgu.controller;

import cn.ictgu.service.model.Hub;
import cn.ictgu.service.model.HubItem;
import cn.ictgu.service.model.User;
import cn.ictgu.service.HubItemService;
import cn.ictgu.service.HubService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class HubItemController {

    private final HubItemService hubItemService;

    private final HubService hubService;

    /**
     * 查看分类的所有子项，仅作者本人可查看
     */
    @GetMapping("/user/hub/{id}")
    public String list(@AuthenticationPrincipal UsernamePasswordAuthenticationToken userAuthentication, @PathVariable("id") Long id, Model model) {
        User user = (User) userAuthentication.getPrincipal();
        List<HubItem> items = hubItemService.list(id, user.getId());
        Hub hub = hubService.getById(id, user.getId());
        model.addAttribute("user", user);
        model.addAttribute("hub", hub);
        model.addAttribute("items", items);
        return "item";
    }

    /**
     * 分类分享，用于暴露给所有用户
     */
    @GetMapping("/share/{md5}")
    public String share(@PathVariable("md5") String md5, Model model) {
        Hub hub = hubService.getByMd5(md5);
        if (hub == null) {
            throw new RuntimeException("仓库不存在！");
        }
        List<HubItem> items = hubItemService.list(hub.getId(), hub.getUserId());
        model.addAttribute("hub", hub);
        model.addAttribute("items", items);
        return "share";
    }

}
