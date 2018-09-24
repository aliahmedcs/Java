package com.magdsoft.sindbad.ws.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.magdsoft.sindbad.ws.controller.Register;

public class Utility {
	public static Map<String, Object> constructJSON(String done, int userId) {
		Map<String, Object> ret = new HashMap<>();
		ret.put("status", "done");
		ret.put("userId", userId);
		return ret;
	}

//	public static String constructJSON(String done, int userId) {
//		JSONObject obj = new JSONObject();
//		JSONArray jArray = new JSONArray();
//		try {
//			obj.put("status", done);
//			obj.put("userId", userId);
//			// jArray.put(obj);
//		} catch (RuntimeException e) {
//			// TODO Auto-generated catch block
//		}
//		return obj.toString();
//	}

	public static Map<String, Object> constructJSON(String done, Integer Id, String name, String email, String phone, String password,
			Integer activationCode, String friendCode, Integer points, String userCode, String userImage, Boolean isActive,
			Date createdAt, Date updatedAt, Integer paymentMethod) {

		Map<String, Object> obj = new HashMap<>();
		try {
			obj.put("status", done);
			obj.put("userId", String.valueOf(Id));
			obj.put("name", name);
			obj.put("email", email);
			obj.put("phone", phone);
			obj.put("password", password);
			obj.put("activationCode", String.valueOf(activationCode));
			obj.put("friendCode", friendCode);
			obj.put("points", String.valueOf(points));
			obj.put("userCode", userCode);
			obj.put("userImage", userImage);
			obj.put("isActive", String.valueOf(isActive));
			obj.put("createdAt", createdAt);
			obj.put("updatedAt", updatedAt);
			obj.put("paymentMethod", paymentMethod);
//*			obj.put("rate", rate);
			// jArray.put(obj);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
		}
		return obj;
	}

//	public static String constructJSON(String done, String userId, String userCode) {
//		JSONObject obj = new JSONObject();
//		// JSONArray jArray=new JSONArray();
//		try {
//			obj.put("status", done);
//			obj.put("userId", userId);
//			obj.put("userCode", userCode);
//
//		} catch (RuntimeException e) {
//			// TODO Auto-generated catch block
//		}
//		return obj.toString();
//	}

	public static Map<String, Object> constructJSON(String done, String userId, String userCode) {
//		JSONObject obj = new JSONObject();
		// JSONArray jArray=new JSONArray();
		Map<String, Object> ret = new HashMap<>();
		try {
			ret.put("status", done);
			ret.put("userId", userId);
			ret.put("userCode", userCode);

		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
		}
		return ret;
	}
	//	public static String constructJSONActivationCode(int activationCode) {
//		JSONObject obj = new JSONObject();
//		// JSONArray jArray=new JSONArray();
//		try {
//			obj.put("status", String.valueOf(activationCode));
//		} catch (RuntimeException e) {
//			// TODO Auto-generated catch block
//		}
//		return obj.toString();
//	}
	public static Map<String, Object> constructJSONActivationCode(int activationCode) {
		//JSONObject obj = new JSONObject();
		// JSONArray jArray=new JSONArray();
		Map<String, Object> obj = new HashMap<>();
		try {
			//obj.put("status", done);
			obj.put("activationCode", String.valueOf(activationCode));
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
		}
		return obj;
	}
	
	public static Map<String, Object> constructJSONGetComplaints(String done,List<Map<String, Object>> complaints){
		Map<String, Object> obj = new HashMap<>();
		
		try {
			obj.put("status", done);
			obj.put("complaints", complaints);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
		}
		return obj;
		
	}

//	public static String constructJSON(String done, int Id, String name, String email, String phone, String password,
//			int activationCode, String friendCode, int points, String userCode, String userImage, boolean isActive,
//			Date createdAt, Date updatedAt, int paymentMethod, int rate) {
//
//		JSONObject obj = new JSONObject();
//		JSONArray jArray = new JSONArray();
//		try {
//			obj.put("status", done);
//			obj.put("userId", String.valueOf(Id));
//			obj.put("name", name);
//			obj.put("email", email);
//			obj.put("phone", phone);
//			obj.put("password", password);
//			obj.put("activationCode", String.valueOf(activationCode));
//			obj.put("friendCode", friendCode);
//			obj.put("points", String.valueOf(points));
//			obj.put("userCode", userCode);
//			obj.put("userImage", userImage);
//			obj.put("isActive", String.valueOf(isActive));
//			obj.put("createdAt", createdAt);
//			obj.put("updatedAt", updatedAt);
//			obj.put("paymentMethod", paymentMethod);
//			obj.put("rate", rate);
//			// jArray.put(obj);
//		} catch (RuntimeException e) {
//			// TODO Auto-generated catch block
//		}
//		return obj.toString();
//	}

	public static Map<String, Object> constructJSONGetHelp(String done,List<Map<String, Object>> glob,List<Map<String, Object>> quest){
		Map<String, Object> obj = new HashMap<>();
		
		try {
			obj.put("status", done);
			obj.put("global", glob);
			obj.put("questions", quest);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
		}
		return obj;
		
	}

	
	

public static Map<String, Object> constructJSONGetHistory(String done,List<Map<String, Object>> trips){
		Map<String, Object> obj = new HashMap<>();
		
		try {
			obj.put("status", done);
			obj.put("trips", trips);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
		}
		return obj;
		
	}
	public static Map<String, Object> constructJSONGetList(String done,List glob){
		Map<String, Object> obj = new HashMap<>();
		
		try {
			obj.put("status", done);
			obj.put("global", glob);
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
		}
		return obj;
		
	}
	
	public static Map<String, Object> constructJSONGetPlaces(String done,List<Map<String, Object>> places){
		Map<String, Object> obj = new HashMap<>();
		
		try {
			obj.put("status", done);
			obj.put("places", places);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
		}
		return obj;
		
	}
	
	public static Map<String, Object> constructJSONGetRequestTrip(String done,Integer userId){
		Map<String, Object> obj = new HashMap<>();
		
		try {
			obj.put("status", done);
			obj.put("userId", userId);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
		}
		return obj;
		
	}

	public static Map<String, Object> constructJSONStack(Object done, String userId, String userCode) {
//		JSONObject obj = new JSONObject();
		// JSONArray jArray=new JSONArray();
		Map<String, Object> ret = new HashMap<>();
		try {
			ret.put("status", done);
			ret.put("userId", userId);
			ret.put("userCode", userCode);

		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
		}
		return ret;
	}
	
	public static Map<String, Object> constructJSONValidate(String done, String userId, int userCode) {
//		JSONObject obj = new JSONObject();
		// JSONArray jArray=new JSONArray();
		Map<String, Object> ret = new HashMap<>();
		try {
			ret.put("status", done);
			ret.put("userId", userId);
			ret.put("userCode", userCode);

		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
		}
		return ret;
	}

	
	/**
	 * Null check Method
	 * 
	 * @param txt
	 * @return
	 */
	public static boolean isNotNull(String txt) {
		// System.out.println("Inside isNotNull");
		return txt != null && txt.trim().length() >= 0 ? true : false;
	}
	public static String uploadFile(MultipartFile fl) throws NoSuchAlgorithmException, IOException {
		String fileName = "UL" + new Date().getTime() + fl.getOriginalFilename();
		File pathFile = new File(Register.PATH);
		pathFile.mkdirs();
		
		Path path = Paths.get(Register.PATH, fileName);
		File file = path.toFile();
		fl.transferTo(file);

		return "https://sindbad.magdsoft.com/public/uploads/" + fileName;
	}

}
