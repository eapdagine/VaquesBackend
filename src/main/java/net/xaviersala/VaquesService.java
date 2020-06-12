package net.xaviersala;

import net.xaviersala.exceptions.VaquesException;
import net.xaviersala.services.CampService;

import static spark.Spark.*;

public class VaquesService {
    static final CampService campServei = new CampService();

    public static void main(String[] args) {

        get("/vaques/start/:quantes", (req, res) -> {
            int quantesVaques = Integer.parseInt(req.params(":quantes"));
            campServei.init(quantesVaques);
            return campServei.getCasa();
        }, new JSONTransformer());

        get("/vaques/camp", (req, res) -> {
            return campServei.getCasa();
        }, new JSONTransformer());

        get("/vaques/camio", (req, res) -> {
            return campServei.getCamio();
        }, new JSONTransformer());

        get("/vaques/ciutat", (req, res) -> {
            return campServei.getCiutat();
        }, new JSONTransformer());

        get("/vaques/posacamio/:nomVaca", (req, res) -> {
            String nom = req.params(":nomVaca");
            try {
                if (campServei.PosaVacaAlCamio(nom)) {
                    return new Result("OK");
                } else {
                    System.out.println("ERROR");
                    res.status(400);
                    return new Result("La vaca pesa massa");
                }
            } catch (VaquesException e) {
                res.status(400);
                return new Result(e.getMessage());
            }
        }, new JSONTransformer());

        get("/vaques/posacamp/:nomVaca", (req, res) -> {
            String nom = req.params(":nomVaca");
            if (campServei.PosaVacaAlCamp(nom)) {
                return new Result("Ok");
            }
            else
            {
                res.status(400);
                return new Result("Quina vaca s'ha de posar?");
            }
        }, new JSONTransformer());

        get("/vaques/tocity", (req, res) -> {
            double kg = campServei.CamioACiutat();
            return kg;
        }, new JSONTransformer());


        exception(VaquesException.class, (exception, request, response) -> {
            response.status(404);
            response.body("{\"message\":\"" + exception.getMessage() + "\"}");
            return;
        });

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        // Capsaleres CORS
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Method", "GET");
            response.header("Access-Control-Allow-Headers", "*");
            response.type("application/json");
        });
    }

}
