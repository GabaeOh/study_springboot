package com.example.study_springboot.restapis;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.study_springboot.service.HelloWorldService;

//클라이언트와 서비스
@Controller //extends HTTPServlet와 동일한 기능// 캡의 종류에 따라서 역할의 정의가 됨 (어느 곳이랑 연결될지, db,server)
public class HelloWorldController {
    
    @Autowired //di 자동으로 됨 (메모리에 끌어올림 자동으로 인스턴스화 할 필요 없음)
    HelloWorldService helloWorldService;

    @GetMapping("/helloWorld") //doGet으로 쓴다는 명기
    public int helloWorld(){
        return 0;
    }
    // /helloWorldGetRequest?Name=yojulab
    // /helloWorldGetRequest/yojulab/U-01
    @GetMapping("/helloWorldGetRequest/{name}/{Id}") //doGet으로 쓴다는 명기
    public int helloWorldGetRequest(@PathVariable String name, @PathVariable String Id){ //@PathVariable 변수에 캡을 씌워져야함 파라미터의 값을 인식해서 값을 넣어줌 
        return 0;
    }

    //해쉬맵
    //?serviceKey=lyGG9gXVU53jKchDovbZiX3F8261gTO4J18wYgnk75nfDz6VDDFUSyTBjrh885eqcb7IBj1Ud6fPDGet2P5Y7g%3D%3D
    //&currentPage=1
    //&perPage=10
    //&SN=1
    // /helloWorldResponse/1/10/1
    @GetMapping("/helloWorldResponse/{currentPage}/{perPage}/{SN}") //doGet으로 쓴다는 명기
    public ResponseEntity<Object> helloWorldResponse(@PathVariable String currentPage
                        , @PathVariable String perPage, @PathVariable String SN){ 
         //"spm_row": 471,"SN": 1,"CMPNM": "로이유통","RDNMADR": null  //해쉬맵으로 db에서 갖고 오는 것                   
        HashMap resultMap = new HashMap<>();
        resultMap.put("spm_row", 471);   
        resultMap.put("SN", 1);
        resultMap.put("CMPNM","로이유통");
        resultMap.put("RDNMADR", null);

        return ResponseEntity.ok().body(resultMap); //json으로 상대한테 결과값을 보내줌 
    }

    // 리스트 
    // /helloWorldResponseList/1/10/1
    @GetMapping("/helloWorldResponseList/{currentPage}/{perPage}/{SN}") 
    public ResponseEntity<Object> helloWorldResponseList(@PathVariable String currentPage
                        , @PathVariable String perPage, @PathVariable String SN){ 
        //"spm_row": 471,"SN": 1,"CMPNM": "로이유통","RDNMADR": null  
        //"spm_row": 571,"SN": 2,"CMPNM": "의료유통","RDNMADR": 3
        ArrayList arrayList = new ArrayList<>();
        HashMap resultMap = new HashMap<>();
        resultMap.put("spm_row", 471);   
        resultMap.put("SN", 1);
        resultMap.put("CMPNM","로이유통");
        resultMap.put("RDNMADR", null);
        arrayList.add(resultMap);

        resultMap = new HashMap<>();
        resultMap.put("spm_row", 571);   
        resultMap.put("SN", 2);
        resultMap.put("CMPNM","의료유통");
        resultMap.put("RDNMADR", 3);
        arrayList.add(resultMap);
                       
        return ResponseEntity.ok().body(arrayList); //json으로 상대한테 결과값을 보내줌 
    }

    // /helloWorldResponseList/1/10/1
    @GetMapping("/helloWorldResponseWithService/{currentPage}/{perPage}/{SN}") 
    public ResponseEntity<Object> helloWorldResponseWithService(@PathVariable String currentPage
                        , @PathVariable String perPage, @PathVariable String SN){ 
        ArrayList arrayList = new ArrayList<>();
        //HelloWorldService helloWorldService = new HelloWorldService(); //해당메서드를 CALL하기 위해 인스턴스화
        arrayList = helloWorldService.fakeSelect(currentPage, perPage); //함수 호출하면서 fakeSelect로 보냄

        return ResponseEntity.ok().body(arrayList); //json으로 상대한테 결과값을 보내줌 
    }

    // /helloWorldResponseFake/C001
    @GetMapping("/helloWorldResponseFake/{companyId}") 
    public ResponseEntity<Object> helloWorldResponseFake(@PathVariable String companyId){ 
        ArrayList arrayList = new ArrayList<>();
        helloWorldService.fakeSelect(companyId); 
        return ResponseEntity.ok().body(arrayList); //json으로 상대한테 결과값을 보내줌 
    }
}
