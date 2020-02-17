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
        add(new HashMap<String, String>() {{ put("companyId", "100"); put("name", "Bill");}});
        add(new HashMap<String, String>() {{ put("companyId", "100"); put("name", "Alex");}});
        add(new HashMap<String, String>() {{ put("companyId", "100"); put("name", "Phil");}});
        add(new HashMap<String, String>() {{ put("companyId", "100"); put("name", "Paul");}});
    }};

    @GetMapping
    public List<Map<String, String>> list(){
        return messages;
    }

    @GetMapping("{companyId}")
    public Map<String, String> getOne(@PathVariable String companyId){
        return getMessage(companyId);
    }

    private Map<String, String> getMessage(@PathVariable String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
