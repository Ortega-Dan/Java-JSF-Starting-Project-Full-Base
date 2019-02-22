package control;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import dtos.RestImageIn;
import dtos.RestResponse;

public class RestImagesControl {

	public static String startBatch(String jsonRecieved) {

		String defresponse = "{ \"codret\" : \"99\", \"msg\" : \"Algo completamente inesperado sucedio;"
				+ " revisar con los administradores o desarrolladores del sistema\" }";

		RestResponse responseObject = null;
		RestImageIn imageInObj;

		Gson gson = new Gson();
		try {
			imageInObj = gson.fromJson(jsonRecieved, RestImageIn.class);

			if ((!imageInObj.getMpadre().matches("M\\d+")) || !imageInObj.getDestpath().matches("sftp://[\\w\\s\\(\\)\\.:,/-]+")
					|| imageInObj.getPags() == 0) {
				throw new JsonSyntaxException("");
			}

			// TODO Validate destpath agains possible SQL injection 
			
			System.out.println(imageInObj);

			responseObject = new RestResponse();
			responseObject.setCodret("00");
			responseObject.setMsg("");

		} catch (Exception e) {
			responseObject = new RestResponse();
			responseObject.setCodret("99");
			responseObject.setMsg("El objeto JSON no cumple con las especificaciones, por favor revisarlas.");
		}

		return (responseObject != null ? gson.toJson(responseObject) : defresponse);
	}

}
