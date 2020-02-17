package letscode.TryREST.controller;

import letscode.TryREST.exception.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("company")
public class MockController {
    private int counter = 5;

    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>(){{
        add(new HashMap<String, String>() {{ put("companyId", "100"); put("name", "Izergil");}});
        add(new HashMap<String, String>() {{ put("companyId", "200"); put("name", "Mick");}});
        add(new HashMap<String, String>() {{ put("companyId", "300"); put("name", "Jagger");}});
        add(new HashMap<String, String>() {{ put("companyId", "400"); put("name", "Bill");}});
        add(new HashMap<String, String>() {{ put("companyId", "400"); put("name", "Paul");}});
    }};

    @GetMapping
    public List<Map<String, String>> list(){
        return messages;
    }

    @GetMapping("{companyId}")
    public Map<String, String> getCompany(@PathVariable String companyId){
        return getMessage(companyId);
    }

    private Map<String, String> getMessage(@PathVariable String companyId) {
        return messages.stream()
                .filter(message -> message.get("companyId").equals(companyId))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
