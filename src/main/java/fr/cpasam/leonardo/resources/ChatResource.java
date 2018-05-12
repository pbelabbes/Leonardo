package fr.cpasam.leonardo.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

import fr.cpasam.leonardo.model.chat.Chat;
import fr.cpasam.leonardo.model.chat.ShopChat;
import fr.cpasam.leonardo.model.chat.ShopChatDAO;
import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.shop.ShopDAO;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.MemberDAO;
import fr.cpasam.leonardo.utilities.Validator;


/**
 * Traitement de la requete d'ouverture d'un chat
 * @param json [shop_id et user_id]
 * @return le chat créé
 */
@Path("/chat")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChatResource {

	@POST
	public Response openChat(JsonObject json) {

		Long shop_id = json.get("shop_id").getAsLong();
		Long user_id = json.get("user_id").getAsLong();

		Member m = MemberDAO.get(user_id);

		Shop s = ShopDAO.get(shop_id);

		Chat c = m.openChat(s);



		return Response.ok(c).build();

	}

	
	/**
	 * Traitement de la requete de récupération d'un chat via son id
	 * @param id du chat
	 * @param json [user_id et token pour vérifier la connexion]
	 * @return le chat correspondant si tout c'est bien passé sinon des message d'erreur [401 : utilisateur non connecté / 406 : mauvais mot de passe / 403 : chat n'appartient pas au membre]
	 */
	@GET
	@Path("/{id}")
	public Response get(@PathParam("id") long id, JsonObject json) {

		long user_id = json.get("user_id").getAsLong();

		// Vérifier que l'utilisateur est bien connecté 
		if(!json.has("user_id")) return Response.status(Response.Status.UNAUTHORIZED).build();

		// Vérifier le jeton CSRF

		String token = json.get("token").getAsString();
		if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();

		//Vérifier que le chat appartient au membre

		ShopChat c = (ShopChat) ShopChatDAO.get(id);
		if((c.getMember().getId()) != user_id && c.getShop().getMember(user_id)==null) return Response.status(Response.Status.FORBIDDEN).build();


		return Response.ok(c).build();
	}

	
	/**
	 * Traitement de la requete pour récupérer les chats correspondant au membre donné
	 * @param json [user_id et token]
	 * @return le chat correspondant si tout c'est bien passé sinon des message d'erreur [401 : utilisateur non connecté / 406 : mauvais mot de passe]
	 */
	@GET
	@Path("?USER")
	public Response get(JsonObject json) {

		// Vérifier que l'utilisateur est bien connecté 
		if(!json.has("user_id")) return Response.status(Response.Status.UNAUTHORIZED).build();
		long user_id = json.get("user_id").getAsLong();
		
		// Vérifier le jeton CSRF

		String token = json.get("token").getAsString();
		if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();

		//Vérifier que le chat appartient au membre

		ArrayList<ShopChat> chats = ShopChatDAO.getByMember(user_id);
		
		return Response.ok(chats).build();

	}
}
