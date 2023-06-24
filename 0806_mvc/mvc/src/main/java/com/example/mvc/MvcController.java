package com.example.mvc;

import com.example.mvc.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

@Controller
public class MvcController {

    private final LottoService lottoService;
    //private int hitCount = 0;

    public MvcController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    @RequestMapping("/hits")
    public String hits(Model model) {
        int hitCount = lottoService.addHit();
        model.addAttribute("hits", hitCount);
        return "hits";
    }

    @RequestMapping("/history")
    public String history(Model model) {

        model.addAttribute("history", "I am lost");
        return "history";
    }

    @RequestMapping("/lotto")
    public String lotto(Model model) {
        //6개의 임의의 숫자 만들기
//        List<Integer> winningNums = new ArrayList<>();
//        RandomGenerator random = new Random();
//        for (int i = 0; i < 6; i++) {
//            winningNums.add(random.nextInt(1, 46));
//        }
        List<Integer> winningNums = lottoService.nextWinningNumber();
        model.addAttribute("winningNums", winningNums);

        return "lotto";
    }

    //history 추가

    @RequestMapping("/")
    public String home(Model model) {

        model.addAttribute("message", "Hello, Tymeleaf! HAHAHAHA");

        return "home";

    }

    @RequestMapping("/student")
    public String student(Model model) {
        model.addAttribute("object", new Student("Jeeho Park", "jeeho@gmail.com"));
        return "student";
    }

    ;

    @RequestMapping("/is-logged-in")
    public String isLoggedIn(Model model) {
        model.addAttribute(
                "isLoggedIn",
                true
        );
        return "if-unless";

    }

    @RequestMapping("/each")
    public String items(Model model) {
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("foo");
        listOfStrings.add("bar");
        listOfStrings.add("baz");

        model.addAttribute("listOfStrings", listOfStrings);
//for (String item : listOfStrings);

        List<Student> studentList = Arrays.asList(
                new Student("Alex", "alex@gmail.com"),
                new Student("Brad", "brad@gmail.com"),
                new Student("Chad", "chad@gmail.com")
        );
        model.addAttribute("studentList", studentList);
        return "each";
    }
/* 혼자서하긴했당

    @RequestMapping("/lotto")
    public String lotto(Model model) {
        List<Long> lottoList = new ArrayList<>();

        double e = Math.random() *45;
        lottoList.add( Math.round(Math.random() *45 + 1));
        lottoList.add( Math.round(Math.random() *45 + 1));
        lottoList.add( Math.round(Math.random() *45 + 1));
        lottoList.add( Math.round(Math.random() *45 + 1));
        lottoList.add( Math.round(Math.random() *45 + 1));
        lottoList.add( Math.round(Math.random() *45 + 1));
        //lottoList.add( Math.random() * 45);


        List<Lotto> lottoList = Arrays.asList(
//                new Lotto(28),
//        lottoList.add(String.valueOf((int) Math.random() * 45));
            new Lotto(String.valueOf((int) Math.random() * 45))
//            new Lotto((int) Math.random() * 45)
        );

        model.addAttribute("lottoList", lottoList);
        return "lotto";

    }

 */


    }




