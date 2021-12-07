package frontcontroller;

import io.javalin.Javalin;

/*Where all endpoints hit first
    * where middleware will be
    * */
public class FrontController {
    public FrontController(Javalin app){
        //MIddle ware

/*        app.before("/protected/*", context -> {
            System.out.println("middleware hit");
        });

        app.after("/protected/*", context -> {
            System.out.println("middleware hit");
        });

        //this is part of one sigle endpoint, getalltodos
        app.get("/protected/getAllTodos", context -> {
            System.out.println();
            context.result("test");
        });

        app.error(404, context -> {
            context.result("This is not a valid endpoint");
        });*/

        app.exception(NumberFormatException.class, (e, context)->{
            context.result("Invalid input! Expected a number");
        });
        new Dispatcher(app);
    }
}
