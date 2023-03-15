package advisor.model;

import advisor.Main;
import advisor.engine.AuthorizationEngine;
import advisor.service.*;

import java.util.Objects;
import java.util.Scanner;

public class Advisor {


    private AuthorizationEngine authorizationEngine;

    private CategoriesService categoriesService;

    private FeatureService featureService;

    private NewService newService;

    private PlayListService playListService;





    public void start(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            String command = scanner.nextLine();
            if(null != command && !Objects.equals("exit",command)){








            }



        }







    };

}
