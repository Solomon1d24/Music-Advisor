package advisor.view;

import advisor.Main;
import advisor.engine.AuthorizationEngine;
import advisor.service.Service;

import java.util.List;

public class ServiceView {

    private List<String> result;

    private int from;

    private int to;

    /**
     * This method is to update the page.
     * @param direction diretion of the page input by user either 'next' or 'prev'
     * */
    public void updateView(String direction) {
        if (direction.equals("next")) {
            if (null == result || from + Main.PAGE >= result.size()){
                throw new ArrayIndexOutOfBoundsException("No more pages.");
            }
            from += Main.PAGE;
            to += Main.PAGE;
        } else {
            if(null == result || from - Main.PAGE < 0){
                throw new ArrayIndexOutOfBoundsException("No more pages.");
            }
            from -= Main.PAGE;
            to -= Main.PAGE;
        }
    }

    /**
     * This method is to print the view based on the page, result and action input by user
     * */
    public void printServiceView() {
        int start = from;
        int end = to;
        if (to > result.size()) {
            end = result.size();
        }
        result.subList(start, end).forEach(System.out::println);
        int totalPage = (int) Math.ceil(result.size() / Main.PAGE);
        int currentPage = end / Main.PAGE;
        String message = String.format("---PAGE %d OF %d---", currentPage, totalPage);
        System.out.println(message);
    }

    /**
     * Thie method is to display the message while executing the setAccessCode and setAccessToken methods in AuthorizationEngine
     * {@link AuthorizationEngine#setAccessCode()}
     * {@link AuthorizationEngine#setAccessToken()}
     * @param authorizationEngine
     * */
    public void printServiceView(AuthorizationEngine authorizationEngine) {
        authorizationEngine.setAccessCode();
        authorizationEngine.setAccessToken();
    }

    /**
     * This method is used to display the message when the user type 'exit' only.
     * */
    public void printExitView() {
        System.out.println("---GOODBYE!---");
    }

    /**
     * This method is the overloaded method of printServiceView to accept the exception when the exception is encountered
     * @see advisor.exception.InvalidCategoryException
     * */
    public void printServiceView(Exception exception) {
        System.out.println(exception.getMessage());
    }

    /**
     * Set the result come from different services
     * @param result A list of String which comes from the getResult method of services
     * {@link Service#getResult()}
     * */
    public void setResult(List<String> result) {
        from = 0;
        to = Main.PAGE;
        this.result = result;
    }

    public ServiceView() {
        from = 0;
        to = Main.PAGE;
    }
}
