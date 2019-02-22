package services;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import control.RestAuthenticationControl;
import control.RestImagesControl;

@Path("/ldgelsa")
public class LdGelsaService {

	@POST
	@Path("/images")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createUser(String jsonRecieved) throws IOException {
		
		// E:\SFTP\Gelsa\InPublicador1
		
		RestAuthenticationControl.authenticate();
		// System.out.println(jsonRecieved);

		return RestImagesControl.startBatch(jsonRecieved);
		
	}

}