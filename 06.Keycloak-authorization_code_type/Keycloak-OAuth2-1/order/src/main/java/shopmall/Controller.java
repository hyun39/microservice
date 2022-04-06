package shopmall;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@RestController
public class Controller {
    @GetMapping("/orders/placeAnOrder")
    //@RolesAllowed({"ROLE_ORDER_CUSTOMER"})
    public String placeAnOrder(Principal principal) {
        return "We've Good Products, You can place an Order. <br><br>User Id:" + principal.getName();
    }

    @GetMapping("/orders/orderManage")
    //@RolesAllowed({"ROLE_ORDER_ADMIN"})
    public String orderManage(Principal principal) {
        return "This is Order Management Site, You can manage Orders. <br><br>User Id:" + principal.getName();
    }

    @GetMapping("/orders/getUser")
    //@PermitAll
    public String getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return String.format("You are [%s] with e-mail address [%s].%n",
                    jwt.getClaimAsString("name"), jwt.getClaimAsString("email"));
        }
        else {
            return "Something went wrong; authentication is not provided by IAP/JWT.\n";
        }

    }
}