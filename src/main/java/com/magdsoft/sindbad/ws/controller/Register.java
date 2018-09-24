package com.magdsoft.sindbad.ws.controller;

import java.io.ByteArrayOutputStream;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

//import com.magdsoft.CarGo.ws.controller.DistanceWebService;
import com.magdsoft.sindbad.ws.forms.AddNewSearch;
import com.magdsoft.sindbad.ws.forms.AddOrUpdateRate;
import com.magdsoft.sindbad.ws.forms.AddPlaceForUser;
import com.magdsoft.sindbad.ws.forms.NewHelp;
import com.magdsoft.sindbad.ws.forms.NewSuggestionForm;
import com.magdsoft.sindbad.ws.forms.PagedUserForm;
import com.magdsoft.sindbad.ws.forms.ReportComplaint;
import com.magdsoft.sindbad.ws.forms.ReportLost;
import com.magdsoft.sindbad.ws.forms.UserWithImages;
import com.magdsoft.sindbad.ws.helpers.password.DefaultPasswordHasher;
import com.magdsoft.sindbad.ws.model.Car;
import com.magdsoft.sindbad.ws.model.Driver;
import com.magdsoft.sindbad.ws.model.DriverCar;
//import com.magdsoft.sindbad.ws.model.DriverPaymentMethod;
import com.magdsoft.sindbad.ws.model.DriverRate;
import com.magdsoft.sindbad.ws.model.FreeRide;
import com.magdsoft.sindbad.ws.model.Help;
import com.magdsoft.sindbad.ws.model.LostItem;
import com.magdsoft.sindbad.ws.model.Promotion;
import com.magdsoft.sindbad.ws.model.Reason;
import com.magdsoft.sindbad.ws.model.Suggestion;
import com.magdsoft.sindbad.ws.model.User;
import com.magdsoft.sindbad.ws.model.UserComplaint;
import com.magdsoft.sindbad.ws.model.UserComplaintImage;
import com.magdsoft.sindbad.ws.model.UserComplaintType;
//import com.magdsoft.sindbad.ws.model.UserPaymentMethod;
import com.magdsoft.sindbad.ws.model.UserPlace;
import com.magdsoft.sindbad.ws.model.UserSearch;
import com.magdsoft.sindbad.ws.model.UserTrip;
import com.magdsoft.sindbad.ws.model.Utility;

@Controller
@RequestMapping("/api")
public class Register {

	public static final int PAGINATION = 10;

	public static final int PAGINATION_GET_HELP = PAGINATION;
	public static final int PAGINATION_GET_HISTORY = PAGINATION;

	public static final String PATH = "/www/sindbadm/java";

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@RequestMapping("/addNewHelp")
	public @ResponseBody Map<String, Object> addNewHelp(NewHelp newHelp,
			@RequestBody(required = false) NewHelp newHelpBody) {
		Map<String, Object> map = new HashMap<>();
		if (newHelpBody != null) {
			newHelp = newHelpBody;
		}
		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			se.getTransaction().begin();
			User user = se.find(User.class, newHelp.getUserId());
			Help help = new Help();
			help.setUser_id(user);
			help.setQuestion(newHelp.getQuestion());
			help.setQuestionTitle(newHelp.getQuestionTitle());
			help.setType("user");
			se.persist(help);
			se.getTransaction().commit();
			map.put("status", "done");
			return map;
			// return Utility.constructJSON("done", null, null);
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;// return
					// Utility.constructJSON(e.getStackTrace().toString(), null,
					// null);
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}

	@RequestMapping("/addNewSearch")
	public @ResponseBody Map<String, Object> addNewSearch(AddNewSearch addNewSearch,
			@RequestBody(required = false) AddNewSearch addNewSearchBody) {
		Map<String, Object> map = new HashMap<>();
		if (addNewSearchBody != null) {
			addNewSearch = addNewSearchBody;
		}
		if (addNewSearch.getLatitude() == null || addNewSearch.getLongitude() == null) {
			return Utility.constructJSON("Error you aren't insert longitude and latitude", null, null);
		}

		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			Boolean insert = false;

			se.getTransaction().begin();
			Query q = se.createQuery(
					"from UserSearch where user_id.id=:id and longitude=:long and latitude=:lat and search=:se");
			q.setParameter("id", addNewSearch.getId());
			q.setParameter("long", addNewSearch.getLongitude());
			q.setParameter("lat", addNewSearch.getLatitude());
			q.setParameter("se", addNewSearch.getSearchName());
			List<UserSearch> list = q.getResultList();
			if (list.size() >= 1) {
				map.put("status", "done");
				return map;
				// return Utility.constructJSON("done", null, null);
			}

			UserSearch search = new UserSearch();
			User user = se.find(User.class, addNewSearch.getId());
			search.setUser_id(user);
			search.setSearsh(addNewSearch.getSearchName());
			search.setLatitude(addNewSearch.getLatitude());
			search.setLongitude(addNewSearch.getLongitude());
			se.persist(search);
			se.getTransaction().commit();
			insert = true;
			if (insert) {
				map.put("status", "done");
				return map;
				// return Utility.constructJSON("done", null, null);
			} else {
				map.put("status", "Erro occurred try please again");
				return map;
			} // return Utility.constructJSON("Erro occurred try please again",
				// null, null);
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;// return
					// Utility.constructJSON(e.getStackTrace().toString(), null,
					// null);
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}

	}

	@RequestMapping("/addNewSuggestion")
	public @ResponseBody Map<String, Object> addNewSuggestion(NewSuggestionForm newSuggestionForm,
			@RequestBody(required = false) NewSuggestionForm newSuggestionFormBody) {
		Map<String, Object> map = new HashMap<>();
		boolean isUserAvailable = false;
		EntityManager se = entityManagerFactory.createEntityManager();
		if (newSuggestionFormBody != null) {
			newSuggestionForm = newSuggestionFormBody;
		}
		try {
			// SessionFactory sf = ((SessionFactory)
			// context.getAttribute("sf"));

			se.getTransaction().begin();
			Suggestion suggestion = new Suggestion();
			User user = se.find(User.class, newSuggestionFormBody.getUserId());
			suggestion.setSuggest(newSuggestionForm.getSuggest());
			suggestion.setUser_id(user);
			se.persist(suggestion);
			se.getTransaction().commit();
			map.put("status", "done");
			return map;
			// return Utility.constructJSON("done", null, null);
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;// return
					// Utility.constructJSON(e.getStackTrace().toString(), null,
					// null);
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}

	}

	@RequestMapping("/addOrUpdateRate")
	public @ResponseBody Map<String, Object> addOrUpdateRate(AddOrUpdateRate addOrUpdateRate,
			@RequestBody(required = false) AddOrUpdateRate addOrUpdateRateBody) {
		Map<String, Object> map = new HashMap<>();
		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			if (addOrUpdateRateBody != null) {
				addOrUpdateRate = addOrUpdateRateBody;
			}
			boolean update = false;
			se.getTransaction().begin();
			// Query q=se.createQuery("update UserTrip set rate=? where id=?");
			// q.setParameter(1, addOrUpdateRate.getRate());
			// q.setParameter(2, addOrUpdateRate.getUserId());
			// q.executeUpdate();
			//// se.getTransaction().commit();
			UserTrip userTrip = se.find(UserTrip.class, addOrUpdateRate.getTripId());
			User user = se.find(User.class, addOrUpdateRate.getUserId());
			if (userTrip == null) {
				// return Utility.constructJSON("Trip-not-found", null, null);
				map.put("status", "Trip-not-found");
				return map;
			}
			if (user == null) {
				map.put("status", "Trip-not-found");
				return map;
				// return Utility.constructJSON("User-not-found", null, null);
			}

			Query q = se.createQuery(
					"from DriverRate dr where dr.driver_id.id = ? and dr.user_id.id=? and dr.trip_id.id=?");
			q.setParameter(1, userTrip.getDriver_id().getId());
			q.setParameter(2, addOrUpdateRate.getUserId());
			q.setParameter(3, addOrUpdateRate.getTripId());

			List<DriverRate> list = q.getResultList();
			for (DriverRate dr : list) {
				update = true;
				dr.setRateValue(addOrUpdateRate.getRate());
				se.persist(dr);
				se.getTransaction().commit();
				// return Utility.constructJSON("done", null, null);
				map.put("status", "done");
				return map;
			}
			DriverRate newDriverRate = new DriverRate();
			Driver driver = se.find(Driver.class, userTrip.getDriver_id().getId());
			// User user=new User();
			// User user=se.find(User.class, addOrUpdateRate.getUserId());

			newDriverRate.setDriver_id(driver);
			newDriverRate.setTrip_id(userTrip);
			newDriverRate.setUser_id(user);
			newDriverRate.setRateValue(addOrUpdateRate.getRate());
			// newDriverRate.setComment(addOrUpdateRate.getComment());
			se.persist(newDriverRate);
			se.getTransaction().commit();
			// return Utility.constructJSON("done", null, null);
			map.put("status", "done");
			return map;
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;// return
					// Utility.constructJSON(e.getStackTrace().toString(), null,
					// null);
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}

	}

	@RequestMapping("/addPlaceForUser")
	public @ResponseBody Map<String, Object> addPlaceForUser(AddPlaceForUser place,
			@RequestBody(required = false) AddPlaceForUser placeBody) {
		Map<String, Object> map = new HashMap<>();
		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			if (placeBody != null) {
				place = placeBody;
			}
			boolean insert = false;

			se.getTransaction().begin();
			UserPlace userPlace = new UserPlace();
			Query q = se.createQuery("from UserPlace as up where up.user_id.id=? and up.latitude=? and up.longitude=?");
			q.setParameter(1, place.getUserId());
			q.setParameter(2, place.getLatitude());
			q.setParameter(3, place.getLongitude());

			List<UserPlace> list = new ArrayList();
			list = q.getResultList();
			for (UserPlace up : list) {
				insert = true;
			}
			if (insert) {
				map.put("status", "done");
				return map;
				// return Utility.constructJSONGetHelp("done", null, null);
			} else if (insert == false) {
				User user = se.find(User.class, place.getUserId());
				userPlace.setUser_id(user);
				userPlace.setCategory(place.getCategory());
				userPlace.setName(place.getName());
				userPlace.setLongitude(place.getLongitude());
				userPlace.setLatitude(place.getLatitude());
				se.persist(userPlace);
				se.getTransaction().commit();
				map.put("status", "done");
				return map;
				// return Utility.constructJSONGetHelp("done", null, null);
			} else {
				map.put("status", "Error please try again");
				return map;
				// return Utility.constructJSONGetHelp("Error please try again",
				// null, null);
			}
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;// return
					// Utility.constructJSONGetHelp(e.getStackTrace().toString(),
					// null, null);
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}

	}

	@RequestMapping("/addPromoCode")
	public @ResponseBody Map<String, Object> addPromoCode(User user, @RequestBody(required = false) User userBody) {
		Map<String, Object> map = new HashMap<>();
		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			if (userBody != null) {
				user = userBody;
			}
			boolean Found = false;
			User userFriend = null;
			se.getTransaction().begin();
			int length = (user.getUserCode() != null ? user.getUserCode().length() : 0);

			if (length == 7) {
				User currentUser = se.find(User.class, user.getId());
				if (currentUser.getFriendCode() != null) {
					se.getTransaction().commit();
					map.put("status", "You have afriend code from before");
					return map;
					// return Utility.constructJSON("You have afriend code from
					// before", null, null);

				} else {
					Query q = se.createQuery("from User where userCode=:uc");
					q.setParameter("uc", user.getUserCode());
					List<User> list = q.getResultList();
					for (User u : list) {
						Found = true;
						userFriend = u;
					}
					if (!Found) {
						se.getTransaction().commit();
						map.put("status", "User code not found");
						return map;
						// return Utility.constructJSON("User code not found",
						// null, null);
					} else {
						q = se.createQuery("from User where userCode=:uc");
						q.setParameter("uc", user.getUserCode());
						List<User> list2 = q.getResultList();
						for (User u : list2) {
							Found = true;
							userFriend = u;
						}
						currentUser.setPoints(currentUser.getPoints() + DistanceWebService.pointsForfriendCode(se));
						userFriend.setPoints(userFriend.getPoints() + DistanceWebService.pointsForfriendCode(se));
						currentUser.setFriendCode(user.getUserCode());
						se.persist(currentUser);
						se.persist(userFriend);
						// se.getTransaction().commit();

						DistanceWebService.addFreeRide(currentUser, se);
						DistanceWebService.addFreeRide(userFriend, se);
						q = se.createQuery("from FreeRide where user_id.id=:id ");
						q.setParameter("id", user.getId());
						List<FreeRide> freeRides = q.getResultList();

						List<Map<String, Object>> objList = new ArrayList();
						// freeRide.put("TotalPoints", currentUser.getPoints());
						for (FreeRide f : freeRides) {
							Map freeRide = new HashMap();
							freeRide.put("id", f.getUser_id().getId());
							freeRide.put("moneyValue", f.getMoneyValue());
							freeRide.put("expireDate", f.getExpiredDate());
							objList.add(freeRide);
						}
						Map<String, Object> ret = new HashMap<>();
						ret.put("status", "done");
						ret.put("totalpoints", currentUser.getPoints());
						ret.put("freeRides", objList);
						se.getTransaction().commit();
						return ret;
					}
				}

			} else {
				int count = 0;
				Promotion promo = null;
				Boolean repeated = false;
				Query q = se.createQuery("from Promotion where code=:c");
				q.setParameter("c", user.getUserCode());
				List<Promotion> list = q.getResultList();
				for (Promotion p : list) {
					promo = p;
				}
				q = se.createQuery("select distinct prm from Promotion prm "
						+ "join prm.user_id u where prm.code = :c and u.id = :id");
				// q=se.createQuery("from Promotion p where
				// p.user_id.user_id=:id");
				q.setParameter("id", user.getId());
				q.setParameter("c", user.getUserCode());
				List<Promotion> list2 = q.getResultList();
				for (Promotion p : list2) {
					repeated = true;
					count++;
				}
				if (repeated) {
					se.getTransaction().commit();
					// return Utility.constructJSON("You get this promo from
					// before", null, null);
					map.put("status", "You get this promo from before");
					return map;
				}

				// Promotion promo=se.find(Promotion.class, user.getUserCode());
				if (promo != null && promo.getExpDate().after(new Date())) {

					User currentUser = se.find(User.class, user.getId());
					currentUser.setPoints(promo.getPoint() + currentUser.getPoints());
					se.persist(currentUser);

					DistanceWebService.addFreeRide(currentUser, se);
					q = se.createQuery("from FreeRide where user_id.id=:id ");
					q.setParameter("id", user.getId());
					List<FreeRide> freeRides = q.getResultList();

					List<Map<String, Object>> objList = new ArrayList();
					// freeRide.put("TotalPoints", currentUser.getPoints());
					for (FreeRide f : freeRides) {
						Map freeRide = new HashMap();
						freeRide.put("id", f.getUser_id().getId());
						freeRide.put("moneyValue", f.getMoneyValue());
						freeRide.put("expireDate", f.getExpiredDate());
						objList.add(freeRide);
					}
					Map<String, Object> ret = new HashMap<>();
					ret.put("totalpoints", currentUser.getPoints());
					ret.put("freeRides", objList);
					currentUser.getPromotion_id().add(promo);
					promo.getUser_id().add(currentUser);
					promo.setCountNow(promo.getCountNow() - 1);
					se.persist(promo);

					se.getTransaction().commit();
					return ret;
				} else {
					se.getTransaction().commit();
					// return Utility.constructJSON("promo is expired or not
					// found", null, null);
					map.put("status", "promo is expired or not found");
					return map;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (se.getTransaction().isActive()) {
				se.getTransaction().rollback();
			}
			// throw e;//return
			// Utility.constructJSON(e.getStackTrace().toString(), null, null);
		} finally {
			// if (se.getTransaction().isActive()) {
			// se.getTransaction().commit(); }
			se.close();
		}
		return null;
	}

	@RequestMapping("/changeStatus")
	public @ResponseBody Map<String, Object> changeStatus(Driver driver,
			@RequestBody(required = false) Driver driverBody) {
		EntityManager se = entityManagerFactory.createEntityManager();
		Map<String, Object> map = new HashMap<>();
		try {
			if (driverBody != null) {
				driver = driverBody;
			}
			boolean insert = false;
			se.getTransaction().begin();
			Query q = se
					.createQuery("select car from DriverCar c left join c.car_id car where" + " c.driver_id.id=:Did");
			q.setParameter("Did", driver.getId());
			Car car = (Car) q.getSingleResult();

			if (car.isOnLine()) {
				car.setOnLine(false);
				se.persist(car);
				se.getTransaction().commit();
				map.put("status", "done not Active");
				return map;

			} else {
				car.setOnLine(true);
				se.persist(car);
				se.getTransaction().commit();
				map.put("status", "done Active");
				return map;
			}
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}

	}

	@RequestMapping("/doLogin")
	public @ResponseBody Map<String, Object> checkLogin(User user, @RequestBody(required = false) User userBody) {
		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			if (userBody != null) {
				user = userBody;
			}

			boolean isUserAvailable = false;
			se.getTransaction().begin();
			Query q = se.createQuery("from User where phone=?");
			q.setParameter(1, user.getPhone());
			String user_id = null;
			List<User> use = q.getResultList();
			for (User u : use) {
				if (u != null) {
					if (DefaultPasswordHasher.getInstance().isPasswordValid(user.getPassword(), u.getPassword())
							&& u.isActive()) {
						isUserAvailable = true;
						return Utility.constructJSON("done", u.getId(), u.getName(), u.getEmail(), u.getPhone(),
								user.getPassword(), u.getActivationCode(), u.getFriendCode(), u.getPoints(),
								u.getUserCode(), u.getUserImage(), u.isActive(), u.getCreatedAt(), u.getUpdatedAt(),
								u.getPaymentMethod());
					} else if (u.isActive() != true && DefaultPasswordHasher.getInstance()
							.isPasswordValid(user.getPassword(), u.getPassword())) {
						return Utility.constructJSONValidate("You not activated yet", "" + u.getId(),
								u.getActivationCode());
					}
				}
			}
			return Utility.constructJSON("your phone or password are not exist", null, null);
		} catch (Exception e) {
			se.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
		return null;
	}

	@RequestMapping("/complaintTypes")
	public @ResponseBody Map<String, Object> complaintTypes() {
		Map<String, Object> map = new HashMap<>();
		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			boolean insert = false;

			se.getTransaction().begin();
			Query q = se.createQuery("from UserComplaintType where type='user'");
			List<UserComplaintType> list = new ArrayList<>();
			insert = true;
			list = q.getResultList();
			List<Map<String, Object>> ret = new ArrayList<>();
			for (UserComplaintType uct : list) {
				map.put("id", uct.getId());
				map.put("complaint title", uct.getName());
				ret.add(map);
			}
			if (insert) {
				map.put("status", "done");
				map.put("complaints", ret);
				return map;
			} else {
				map.put("status", "Error occurred please try again");
				return map;
			}
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}

	}

	@RequestMapping("/deleteUserPlaces")
	public @ResponseBody Map<String, Object> deleteUserPlaces(AddPlaceForUser place,
			@RequestBody(required = false) AddPlaceForUser placeBody) {
		Map<String, Object> map = new HashMap<>();
		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			if (placeBody != null) {
				place = placeBody;
			}

			se.getTransaction().begin();
			Query q = se.createQuery("delete from UserPlace as up where up.user_id.id=? and up.id=?");
			q.setParameter(1, place.getUserId());
			q.setParameter(2, place.getPlaceId());
			int mdification = q.executeUpdate();
			se.getTransaction().commit();
			if (mdification != 0) {
				map.put("status", "done");
				return map;
			} else {
				map.put("status", "There is no places");
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}

	@RequestMapping("/driverAddNewHelp")
	public @ResponseBody Map<String, Object> driverAddNewHelp(NewHelp newHelp,
			@RequestBody(required = false) NewHelp newHelpBody) {
		Map<String, Object> map = new HashMap<>();
		if (newHelpBody != null) {
			newHelp = newHelpBody;
		}
		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			se.getTransaction().begin();
			Driver driver = se.find(Driver.class, newHelp.getUserId());
			Help help = new Help();
			help.setDriver_id(driver);
			help.setQuestion(newHelp.getQuestion());
			help.setQuestionTitle(newHelp.getQuestionTitle());
			help.setType("driver");
			se.persist(help);
			se.getTransaction().commit();
			map.put("status", "done");
			return map;
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}

	@RequestMapping("/driverComplaintTypes")
	public @ResponseBody Map<String, Object> driverComplaintTypes() {
		EntityManager se = entityManagerFactory.createEntityManager();
		Map<String, Object> map2 = new HashMap<>();
		try {
			boolean insert = false;

			se.getTransaction().begin();
			Query q = se.createQuery("from UserComplaintType where type='driver'");
			List<UserComplaintType> list = new ArrayList<>();
			insert = true;
			list = q.getResultList();
			List<Map<String, Object>> ret = new ArrayList<>();
			for (UserComplaintType uct : list) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", uct.getId());
				map.put("complaint title", uct.getName());
				ret.add(map);
			}
			if (insert) {
				map2.put("status", "done");
				map2.put("complaints", ret);
				return map2;
			} else {
				map2.put("status", "Error occurred please try again");
				return map2;
			}
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}

	@RequestMapping("/driverForgetPassword")
	public @ResponseBody Map<String, Object> driverForgetPassword(Driver driver,
			@RequestBody(required = false) Driver driverBody) {
		Map<String, Object> map = new HashMap<>();
		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			if (driverBody != null) {
				driver = driverBody;
			}
			boolean isUserAvailable = false;

			se.getTransaction().begin();
			Query q = se.createQuery("from Driver where email=?");
			q.setParameter(1, driver.getEmail());

			int driver_id = 0;

			List<Driver> driv = q.getResultList();

			for (Driver u : driv) {
				driver_id = u.getId();
				sendEmail(u.getEmail(), u.getActivationCode());
				isUserAvailable = true;
			}
			if (isUserAvailable) {
				map.put("status", "done");
				map.put("userId", driver_id);
				return map;

			} else {
				map.put("status", "error");
				return map;
			}
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}

	@RequestMapping("/driverGetHelp")
	public @ResponseBody Map<String, Object> driverGetHelp(PagedUserForm getHelpForms,
			@RequestBody(required = false) PagedUserForm getHelpFormsBody) {
		Map<String, Object> map = new HashMap<>();
		if (getHelpFormsBody != null) {
			getHelpForms = getHelpFormsBody;
		}
		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			Boolean isFound = false;

			se.getTransaction().begin();
			Query q = se
					.createQuery("from Help help where help.user_id.id=? and help.isGlobal=true and type='driver' ");
			q.setParameter(1, getHelpForms.getId());
			q.setFirstResult((getHelpForms.getPage() - 1) * PAGINATION_GET_HELP);
			q.setMaxResults(PAGINATION_GET_HELP);
			List<Help> globalQuestion = q.getResultList();

			List<Map<String, Object>> globalQus = new ArrayList<>();
			List<Map<String, Object>> questions = new ArrayList<>();
			for (Help u : globalQuestion) {
				isFound = true;
				Map<String, Object> oneMap = new HashMap<>();
				oneMap.put("id", u.getId());
				oneMap.put("question_title", u.getQuestionTitle());
				oneMap.put("answer", u.getAnswer());
				globalQus.add(oneMap);
			}
			Query q2 = se
					.createQuery("from Help help where help.user_id.id=? and help.isGlobal=false and type='driver' ");
			q2.setParameter(1, getHelpForms.getId());
			q2.setFirstResult((getHelpForms.getPage() - 1) * PAGINATION_GET_HELP);
			q2.setMaxResults(PAGINATION_GET_HELP);
			List<Help> notGlobalQuestion = q2.getResultList();
			for (Help u1 : notGlobalQuestion) {
				isFound = true;
				Map<String, Object> questMap = new HashMap<>();
				questMap.put("id", u1.getId());
				questMap.put("question title", u1.getQuestionTitle());
				questMap.put("answer", u1.getAnswer());
				questions.add(questMap);
			}
			try {
				se.getTransaction().commit();
			} catch (RollbackException ex) {
				se.getTransaction().rollback();
			}
			if (isFound) {
				map.put("status", "done");
				map.put("global", globalQus);
				map.put("questions", questions);
				return map;
			} else {
				map.put("status", "there is no help");
				return map;
			}

		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}

	}

	@RequestMapping("/driverLogin")
	public @ResponseBody Map<String, Object> driverLogin(Driver driver,
			@RequestBody(required = false) Driver driverBody) {
		Map<String, Object> map = new HashMap<>();
		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			if (driverBody != null) {
				driver = driverBody;
			}

			boolean isUserAvailable = false;
			se.getTransaction().begin();

			Query q = se.createQuery("from Driver where phone=:ph");
			q.setParameter("ph", driver.getPhone());
			String driver_id = null;
			List<Driver> drive = q.getResultList();
			for (Driver u : drive) {
				if (u != null) {
					if (DefaultPasswordHasher.getInstance().isPasswordValid(driver.getPassword(), u.getPassword())
							&& u.isActive()) {

						isUserAvailable = true;

						Map<String, Object> obj = new HashMap<>();
						obj.put("status", "done");
						obj.put("id", u.getId());
						obj.put("name", u.getName());
						obj.put("email", u.getEmail());
						obj.put("phone", u.getPhone());
						obj.put("password", u.getPassword());
						obj.put("profileImage", u.getProfileImage());
						obj.put("isActive", u.isActive());
						obj.put("job", u.getJob());
						obj.put("age", u.getAge());
						obj.put("address", u.getAddress());
						obj.put("militaryStatus", u.getCreatedAt());
						obj.put("socialStatus", u.getSocialStatus());
						obj.put("idNumber", u.getIdNumber());
						obj.put("payment_method", "null");

						List<Map<String, Object>> list = new ArrayList<>();
						Iterator<DriverCar> iterator = u.getDriver_car().iterator();
						int i = 0;
						while (iterator.hasNext()) {
							DriverCar curr = iterator.next();
							Map<String, Object> asMap = new HashMap<>();
							asMap.put("id", curr.getId());
							asMap.put("startAt", curr.getStarteAt());
							asMap.put("carId", curr.getCar_id().getId());
							asMap.put("updatedAt", curr.getUpdatesAt());
							asMap.put("createdAt", curr.getCreatedAt());
							list.add(asMap);
							i++;
							if (i > 10) {
								break;
							}

						}
						obj.put("driver_car", list);
						obj.put("createdAt", u.getCreatedAt());
						obj.put("updatedAt", u.getUpdatedAt());
						obj.put("startAt", u.getStartAt());
						obj.put("endAt", u.getEndAt());
						obj.put("activationCode", u.getActivationCode());
						return obj;

					} else if (u.isActive() != true && DefaultPasswordHasher.getInstance()
							.isPasswordValid(driver.getPassword(), u.getPassword())) {
						map.put("status", "You not activated yet");
						map.put("userId", u.getId());
						map.put("userCode", u.getActivationCode());
						return map;
					}
				}

			}

			map.put("status", "your phone or password are not exist");
			return map;
		} catch (Exception e) {
			se.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
		return null;
	}

	@RequestMapping("/driverReportComplaint")
	public @ResponseBody Map<String, Object> driverReportComplaint(ReportComplaint complaint) {
		EntityManager se = entityManagerFactory.createEntityManager();
		Map<String, Object> map = new HashMap<>();
		try {
			se.getTransaction().begin();
			boolean insert = false;
			UserComplaint userComplaint = new UserComplaint();
			User user = se.find(User.class, complaint.getUserId());
			UserTrip userTrip = se.find(UserTrip.class, complaint.getTripId());
			UserComplaintType userComplaintType = se.find(UserComplaintType.class, complaint.getComplaintTypeId());
			userComplaint.setTrip_id(userTrip);
			// * userComplaint.setUser_id(user);
			userComplaint.setComplaint_type(userComplaintType);
			userComplaint.setType("driver");
			userComplaint.setActive(false);
			se.persist(userComplaint);
			List<MultipartFile> image = new ArrayList<>();
			image = complaint.getImage();
			if (image == null) {
				image = new ArrayList<>();
			}
			List<String> ret = new ArrayList<>();
			for (MultipartFile fl : image) {
				UserComplaintImage complaintImage = new UserComplaintImage();
				complaintImage.setUser_complaint(userComplaint);
				complaintImage.setImage(Utility.uploadFile(fl));
				se.persist(complaintImage);
			}

			se.getTransaction().commit();
			insert = true;
			if (insert) {
				map.put("status", "done");
				return map;
			} else {
				map.put("status", "Error occcurred please try again");
				return map;
			}
		} catch (Exception e) {
			se.getTransaction().rollback();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(bos));
			return Utility.constructJSON(bos.toString(), null, null);
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}

	}

	@RequestMapping("/driverResetPassword")
	public @ResponseBody Map<String, Object> driverResetPassword(Driver driver,
			@RequestBody(required = false) Driver driverBody) {
		Map<String, Object> map = new HashMap<>();
		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			if (driverBody != null) {
				driver = driverBody;
			}

			se.getTransaction().begin();

			Query q = se.createQuery("update Driver set password=? where id=?");
			q.setParameter(1, DefaultPasswordHasher.getInstance().hashPassword(driver.getPassword()));
			q.setParameter(2, driver.getId());
			int modifications = q.executeUpdate();
			se.getTransaction().commit();
			if (modifications > 0) {
				map.put("status", "done");
				return map;
			} else {
				map.put("status", "error occurred please try again");
				return map;
			}
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}

	@RequestMapping("/doForgetPassword")
	public @ResponseBody Map<String, Object> forgetPassword(User user, @RequestBody(required = false) User userBody) {
		EntityManager se = entityManagerFactory.createEntityManager();
		Map<String, Object> map = new HashMap<>();
		try {
			if (userBody != null) {
				user = userBody;
			}
			boolean isUserAvailable = false;

			se.getTransaction().begin();
			Query q = se.createQuery("from User where email=?");
			q.setParameter(1, user.getEmail());

			int user_id = 0;

			List<User> use = q.getResultList();

			for (User u : use) {
				user_id = u.getId();
				sendEmail(u.getEmail(), u.getActivationCode());
				isUserAvailable = true;
			}
			if (isUserAvailable) {
				return Utility.constructJSON("done", user_id);

			} else {
				map.put("status", "error");
				return map;
			}
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}

	@RequestMapping("/getCarInfo")
	public @ResponseBody Map<String, Object> getCarInfo(Driver driver,
			@RequestBody(required = false) Driver driverBody) {
		EntityManager se = entityManagerFactory.createEntityManager();

		try {
			if (driverBody != null) {
				driver = driverBody;
			}

			se.getTransaction().begin();
			Query q = se
					.createQuery("select car from DriverCar c left join c.car_id car where" + " c.driver_id.id=:Did");
			q.setParameter("Did", driver.getId());
			Car car = (Car) q.getSingleResult();
			Map<String, Object> map = new HashMap<>();
			if (car != null) {
				map.put("status", "done");
				map.put("carNumber", car.getCarNumber());
				map.put("model", car.getModel());
				map.put("manufacturer", car.getManufacturer());
				map.put("licenseNumber", car.getLicenseNumber());
				return map;
			} else {
				map.put("error", "threre no car");
				return map;
			}
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}

	@RequestMapping("/getHelp")
	public @ResponseBody Map<String, Object> getHelp(PagedUserForm getHelpForms,
			@RequestBody(required = false) PagedUserForm getHelpFormsBody) {
		Map<String, Object> map = new HashMap<>();
		if (getHelpFormsBody != null) {
			getHelpForms = getHelpFormsBody;
		}
		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			Boolean isFound = false;

			se.getTransaction().begin();
			Query q = se.createQuery("from Help help where help.user_id.id=? and help.isGlobal=true and type='user' ");
			q.setParameter(1, getHelpForms.getId());
			q.setFirstResult((getHelpForms.getPage() - 1) * PAGINATION_GET_HELP);
			q.setMaxResults(PAGINATION_GET_HELP);
			List<Help> globalQuestion = q.getResultList();

			List<Map<String, Object>> globalQus = new ArrayList<>();
			List<Map<String, Object>> questions = new ArrayList<>();
			for (Help u : globalQuestion) {
				isFound = true;
				Map<String, Object> oneMap = new HashMap<>();
				oneMap.put("id", u.getId());
				oneMap.put("question_title", u.getQuestionTitle());
				oneMap.put("answer", u.getAnswer());
				globalQus.add(oneMap);
			}
			Query q2 = se
					.createQuery("from Help help where help.user_id.id=? and help.isGlobal=false and type='user' ");
			q2.setParameter(1, getHelpForms.getId());
			q2.setFirstResult((getHelpForms.getPage() - 1) * PAGINATION_GET_HELP);
			q2.setMaxResults(PAGINATION_GET_HELP);
			List<Help> notGlobalQuestion = q2.getResultList();
			for (Help u1 : notGlobalQuestion) {
				isFound = true;
				Map<String, Object> questMap = new HashMap<>();
				questMap.put("id", u1.getId());
				questMap.put("question title", u1.getQuestionTitle());
				questMap.put("answer", u1.getAnswer());
				questions.add(questMap);
			}
			try {
				se.getTransaction().commit();
			} catch (RollbackException ex) {
				se.getTransaction().rollback();
			}
			if (isFound) {
				map.put("status", "done");
				map.put("global", globalQus);
				map.put("questions", questions);
				return map;
			} else {
				map.put("status", "there is no help");
				return map;
			}
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}

	@RequestMapping("/getHistoryForDriver")
	public @ResponseBody Map<String, Object> getHistoryForDriver(PagedUserForm pagedUserForm,
			@RequestBody(required = false) PagedUserForm pagedUserFormBody) {
		Map<String, Object> map2 = new HashMap<>();

		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			if (pagedUserFormBody != null) {
				pagedUserForm = pagedUserFormBody;
			}

			Date date;
			int rateValue, driverId, carId;
			Driver driver = new Driver();
			Car car = new Car();
			boolean isGet = false;

			String time;

			se.getTransaction().begin();
			User newUser = se.find(User.class, pagedUserFormBody.getId());
			Query q = se.createQuery("from UserTrip as uT where uT.driver_id.id=? ORDER BY uT.id DESC");
			q.setParameter(1, pagedUserFormBody.getId());
			q.setFirstResult((pagedUserFormBody.getPage() - 1) * PAGINATION_GET_HISTORY);
			q.setMaxResults(PAGINATION_GET_HISTORY);
			List<UserTrip> userTrip = q.getResultList();
			List<Map<String, Object>> ret = new ArrayList<>();
			for (UserTrip t : userTrip) {
				isGet = true;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", t.getId());
				map.put("startAdd", t.getStartAtAddress());
				map.put("endAdd", t.getEndAtAddress());

				String name = "";

				map.put("userPaymentMethodName", name);
				map.put("realCost", t.getRealCost());
				map.put("date", t.getStartAt());
				date = t.getStartAt();
				SimpleDateFormat timeFormate = new SimpleDateFormat("HH:mm:SS");
				time = timeFormate.format(date);

				if (t.getUser_id() != null) {

					map.put("userId", t.getUser_id().getId());
					map.put("userName", t.getUser_id().getName());
					map.put("userImage", t.getUser_id().getUserImage());
					map.put("userPhone", t.getUser_id().getPhone());
				} else {
					map.put("userId", "-1");
					map.put("userName", "NO ONE");
					map.put("userImage", "");
					map.put("userPhone", "");
				}
				ret.add(map);
			}
			if (isGet) {
				map2.put("status", "done");
				map2.put("trips", ret);
				return map2;
			} else {
				map2.put("status", "there is no trip");
				return map2;
			}
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}

	@RequestMapping("/getHistoryForUser")
	public @ResponseBody Map<String, Object> getHistoryUser(PagedUserForm pagedUserForm,
			@RequestBody(required = false) PagedUserForm pagedUserFormBody) {
		Map<String, Object> obj = new HashMap<>();
		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			if (pagedUserFormBody != null) {
				pagedUserForm = pagedUserFormBody;
			}

			Date date;
			int rateValue, driverId, carId;
			Driver driver = new Driver();
			Car car = new Car();
			boolean isGet = false;

			String time;

			se.getTransaction().begin();
			User newUser = se.find(User.class, pagedUserFormBody.getId());
			Query q = se.createQuery(
					"from UserTrip as uT where uT.user_id.id=? and uT.driver_id.id!=null ORDER BY uT.id DESC");
			q.setParameter(1, pagedUserFormBody.getId());
			q.setFirstResult((pagedUserFormBody.getPage() - 1) * PAGINATION_GET_HISTORY);
			q.setMaxResults(PAGINATION_GET_HISTORY);
			List<UserTrip> userTrip = q.getResultList();
			List<Map<String, Object>> ret = new ArrayList<>();
			for (UserTrip t : userTrip) {
				isGet = true;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", t.getId());
				map.put("startAdd", t.getStartAtAddress());
				map.put("endAdd", t.getEndAtAddress());
				map.put("rate", t.getRate());
				map.put("realCost", t.getRealCost());
				map.put("date", t.getStartAt());
				date = t.getStartAt();
				SimpleDateFormat timeFormate = new SimpleDateFormat("HH:mm:SS");
				time = timeFormate.format(date);

				if (t.getDriver_id() != null) {
					map.put("driverId", t.getDriver_id().getId());
					map.put("driverName", t.getDriver_id().getName());
					map.put("driverImage", t.getDriver_id().getProfileImage());
					map.put("driverPhone", t.getDriver_id().getPhone());
				} else {
					map.put("driverId", "-1");
					map.put("driverName", "NO ONE");
					map.put("driverImage", "");
					map.put("driverPhone", "");
				}
				if (t.getCar_id() != null) {
					map.put("carId", t.getCar_id().getId());
					map.put("carName", t.getCar_id().getName());
					map.put("carManufacturer", t.getCar_id().getManufacturer());
					map.put("carModel", t.getCar_id().getModel());
					map.put("carColor", t.getCar_id().getColor());
				} else {
					map.put("carId", -1);
					map.put("carName", "NO ONE");
					map.put("carManufacturer", "");
					map.put("carModel", "");
					map.put("carColor", "");
				}
				ret.add(map);
			}
			if (isGet) {
				obj.put("status", "done");
				obj.put("trips", ret);
				return obj;
			} else {
				obj.put("status", "there is no trip");
				return obj;
			}
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;

		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}

	}

	@RequestMapping("/getUserPlaces")
	public @ResponseBody Map<String, Object> getUserPlaces(AddPlaceForUser place,
			@RequestBody(required = false) AddPlaceForUser placeBody) {
		Map<String, Object> map = new HashMap<>();
		EntityManager se = entityManagerFactory.createEntityManager();
		try {

			if (placeBody != null) {
				place = placeBody;
			}
			boolean insert = false;

			se.getTransaction().begin();
			Query q = se.createQuery("from UserPlace as up where up.user_id.id=?");
			q.setParameter(1, place.getUserId());
			List<UserPlace> list = new ArrayList<>();
			list = q.getResultList();
			List<Map<String, Object>> places = new ArrayList<>();
			for (UserPlace up : list) {
				insert = true;
				Map<String, Object> p = new HashMap();
				p.put("id", up.getId());
				p.put("name", up.getName());
				p.put("category", up.getCategory());
				p.put("latitude", up.getLatitude());
				p.put("longitude", up.getLongitude());
				places.add(p);
			}
			se.getTransaction().commit();
			if (insert) {
				map.put("status", "done");
				map.put("places", places);
				return map;
			} else {
				map.put("status", "There is no places");
				return map;
			}
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}

	}

	@RequestMapping("/doRegister")
	public @ResponseBody Map<String, Object> insertUser(User user, @RequestBody(required = false) User userBody) { // @Param
		EntityManager se = entityManagerFactory.createEntityManager();
		Map<String, Object> map = new HashMap<>();
		try {
			if (userBody != null) {
				user = userBody;
			}

			boolean insertStatus = false;

			se.getTransaction().begin();
			boolean phoneExist = false;
			Query qp = se.createQuery("from User where  phone=?");
			qp.setParameter(1, user.getPhone());
			List<User> usep = qp.getResultList();
			for (User u : usep) {
				phoneExist = true;
			}

			boolean emailExist = false;
			Query qe = se.createQuery("from User where  email=?");
			qe.setParameter(1, user.getEmail());
			List<User> usee = qe.getResultList();
			for (User u : usee) {
				emailExist = true;
			}

			if (phoneExist == false && emailExist == false) {
				User nuser = new User();
				nuser.setName(user.getName());
				nuser.setPhone(user.getPhone());
				nuser.setPassword(DefaultPasswordHasher.getInstance().hashPassword(user.getPassword()));
				nuser.setEmail(user.getEmail());
				nuser.setFriendCode(user.getFriendCode());
				// generated
				String userCode1 = null;

				int randomNum = ThreadLocalRandom.current().nextInt(100000, 999999 + 1);
				nuser.setActivationCode(randomNum);
				se.persist(nuser);

				se.getTransaction().commit();
				String user_id = null;
				se.getTransaction().begin();
				Query q = se.createQuery("from User where  phone=?");
				q.setParameter(1, user.getPhone());
				List<User> use = q.getResultList();
				for (User u : use) {
					Random rand = new Random();
					int num = rand.nextInt(100000);
					user_id = String.valueOf(u.getId());
					String sub = user.getName().substring(0, 3);
					num = rand.nextInt(100000);
					String formatted = String.format("%05d", num);
					formatted = String.format("%04d", num);
					userCode1 = sub + formatted;
				}
				q = se.createQuery("update User set userCode=? where  phone=?");
				q.setParameter(1, userCode1);
				q.setParameter(2, user.getPhone());
				int modifications = q.executeUpdate();
				se.getTransaction().commit();
				if (modifications != 0) {
					insertStatus = true;
				}

				if (insertStatus) {
					sendEmail(nuser.getEmail(), nuser.getActivationCode());
					return Utility.constructJSON("done", user_id, userCode1);

				} else {
					map.put("status", "not registered try again");
					return map;
				}
			} else if (phoneExist == true && emailExist == true) {
				map.put("status", "your phone and email is exist");
				return map;
			} else if (emailExist == true && phoneExist == false) {
				map.put("status", "your Email is exist");
				return map;
			} else if (emailExist == false && phoneExist == true) {
				map.put("status", "your phone is exist");
				return map;
			}
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}

		return null;
	}

	@RequestMapping("/doLoginSocial")
	public @ResponseBody Map<String, Object> loginSocial(User user, @RequestBody(required = false) User userBody) {
		EntityManager se = entityManagerFactory.createEntityManager();
		Map<String, Object> map = new HashMap<>();
		try {
			if (userBody != null) {
				user = userBody;
			}

			se.getTransaction().begin();
			Query q = se.createQuery("from User u where u.email=?");
			q.setParameter(1, user.getEmail());
			List<User> use = q.getResultList();
			for (User u : use) {
				if (!u.isActive()) {
					map.put("status", "your email isn't active");
					map.put("userId", u.getId());
					return map;
				}
				if (u.getEmail() != null) {
					return Utility.constructJSON("done", u.getId(), u.getName(), u.getEmail(), u.getPhone(),
							u.getPassword(), u.getActivationCode(), u.getFriendCode(), u.getPoints(), u.getUserCode(),
							u.getUserImage(), u.isActive(), u.getCreatedAt(), u.getUpdatedAt(), u.getPaymentMethod());
				}
			}
			map.put("status", "your email isn't exist");
			return map;
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}

	@RequestMapping("/setNewPassword")
	public @ResponseBody Map<String, Object> newPassword(User user, @RequestBody(required = false) User userBody) {
		EntityManager se = entityManagerFactory.createEntityManager();
		Map<String, Object> map = new HashMap<>();
		try {
			if (userBody != null) {
				user = userBody;
			}

			se.getTransaction().begin();

			Query q = se.createQuery("update User set password=? where id=?");
			q.setParameter(1, DefaultPasswordHasher.getInstance().hashPassword(user.getPassword()));
			q.setParameter(2, user.getId());
			int modifications = q.executeUpdate();
			se.getTransaction().commit();
			if (modifications > 0) {
				map.put("status", "done");
				return map;
			} else {
				map.put("status", "error");
				return map;
			}
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}

	@RequestMapping("/reasons")
	public @ResponseBody Map<String, Object> reasons() {
		Map<String, Object> map = new HashMap<>();
		EntityManager se = entityManagerFactory.createEntityManager();
		try {

			boolean insert = false;

			se.getTransaction().begin();
			Query q = se.createQuery("from Reason");
			List<Reason> list = new ArrayList<>();

			list = q.getResultList();
			List<Map<String, Object>> ret = new ArrayList<>();
			for (Reason ur : list) {
				if (ur == null) {
					map.put("status", "There is no reason");
					return map;
				}
				insert = true;
				map.put("id", ur.getId());
				map.put("reason", ur.getReason());
				ret.add(map);
			}
			if (insert) {
				map.put("status", "done");
				map.put("complaints", ret);
				return map;
			} else
				map.put("status", "Error occurred please try again");
			return map;
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}

	@RequestMapping("/reportComplaint")
	public @ResponseBody Map<String, Object> reportComplaint(ReportComplaint complaint) {
		Map<String, Object> map = new HashMap<>();
		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			se.getTransaction().begin();
			boolean insert = false;
			UserComplaint userComplaint = new UserComplaint();
			User user = se.find(User.class, complaint.getUserId());
			UserTrip userTrip = se.find(UserTrip.class, complaint.getTripId());
			UserComplaintType userComplaintType = se.find(UserComplaintType.class, complaint.getComplaintTypeId());
			userComplaint.setTrip_id(userTrip);
			// userComplaint.setUser_id(user);
			userComplaint.setComplaint_type(userComplaintType);
			userComplaint.setType("user");
			userComplaint.setActive(false);
			se.persist(userComplaint);
			List<MultipartFile> image = new ArrayList<>();
			image = complaint.getImage();
			if (image == null) {
				image = new ArrayList<>();
			}
			List<String> ret = new ArrayList<>();
			for (MultipartFile fl : image) {
				UserComplaintImage complaintImage = new UserComplaintImage();
				// complaintImage.setPath(complaint.getComplaintDescription());
				complaintImage.setUser_complaint(userComplaint);
				complaintImage.setImage(Utility.uploadFile(fl));
				se.persist(complaintImage);
			}
			se.getTransaction().commit();
			insert = true;
			if (insert) {
				map.put("status", "done");
				return map;
			} else {
				map.put("status", "Error occcurred please try again");
				return map;
			}
		} catch (Exception e) {
			se.getTransaction().rollback();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(bos));
			return Utility.constructJSON(bos.toString(), null, null);
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}

	}

	@RequestMapping("/reportLost")
	public @ResponseBody Map<String, Object> reportLost(ReportLost lost,
			@RequestBody(required = false) ReportLost userBody) {
		Map<String, Object> map = new HashMap<>();
		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			if (userBody != null) {
				lost = userBody;
			}
			se.getTransaction().begin();
			LostItem lostItem = new LostItem();
			UserTrip userTrip = se.find(UserTrip.class, lost.getTripId());
			User user = se.find(User.class, lost.getUserID());
			if (userTrip == null) {
				map.put("status", "Trip-not-found");
				return map;
			}
			if (user == null) {
				map.put("status", "User-not-found");
				return map;
			}
			lostItem.setUser_id(user);
			lostItem.setTrip_Id(userTrip);
			se.persist(lostItem);
			se.getTransaction().commit();
			map.put("status", "done");
			return map;
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}

	@RequestMapping("/retrieve")
	public @ResponseBody Map<String, Object> retrieve() {
		EntityManager se = entityManagerFactory.createEntityManager();
		se.getTransaction().begin();
		Query q = se.createQuery("from Car where id=1");
		List<Driver> list = q.getResultList();
		Map<String, Object> map = new HashMap<>();
		map.put("status", list);
		return map;
	}

	@RequestMapping("/sendCodeToEmail")
	public @ResponseBody Map<String, Object> sendCodeToEmail(User user, @RequestBody(required = false) User userBody) {
		Map<String, Object> map = new HashMap<>();
		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			if (userBody != null) {
				user = userBody;
			}

			se.getTransaction().begin();
			Query q = se.createQuery("from User where phone=?");
			q.setParameter(1, user.getPhone());
			List<User> list = new ArrayList<>();
			list = q.getResultList();
			se.getTransaction().commit();
			for (User u : list) {
				System.out.println("Sending email");
				Integer userCode = u.getActivationCode();
				// TODO: send this to the email.
				sendEmail(user.getEmail(), userCode);
			}
			map.put("status", "done");
			return map;
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}
	
	@RequestMapping("/sendCodeToExistingEmail")
	public @ResponseBody Map<String, Object> sendCodeToExistingEmail(User user, @RequestBody(required = false) User userBody) {
		Map<String, Object> map = new HashMap<>();
		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			if (userBody != null) {
				user = userBody;
			}

			se.getTransaction().begin();
			Query q = se.createQuery("from User where email=?");
			q.setParameter(1, user.getEmail());
			List<User> list = new ArrayList<>();
			list = q.getResultList();
			se.getTransaction().commit();
			for (User u : list) {
				System.out.println("Sending email");
				Integer userCode = u.getActivationCode();
				// TODO: send this to the email.
				sendEmail(user.getEmail(), userCode);
			}
			map.put("status", "done");
			return map;
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}
	
	private void sendEmail(String email, Integer code) {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.host", "email.magdsoft.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        sender.setHost("email.magdsoft.com");
        sender.setPort(587);
        sender.setJavaMailProperties(props);
        sender.setUsername("sindbad@magdsoft.com");
        sender.setPassword("sindysindy12");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("sindbad@magdsoft.com");
        msg.setSubject("Your Sindbad Verification Code");
        msg.setText("" + code);
        msg.setTo(email);
        sender.send(msg);
	}

	@RequestMapping("/updateUserData")
	public @ResponseBody Map<String, Object> updateUserData(UserWithImages userWithImages, BindingResult res) {
		Map<String, Object> map = new HashMap<>();
		if (res.hasErrors()) {
			Map<String, Object> ret = new HashMap<>();

			ret.put("errors", res.getAllErrors());
			return ret;
		}

		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			UserWithImages user = userWithImages;

			se.getTransaction().begin();
			User u = se.find(User.class, user.getId());
			if (user.getName() != null) {
				u.setName(user.getName());
			}
			if (user.getPhone() != null) {
				u.setPhone(user.getPhone());
			}
			try {
				if (user.getImage() != null)
					u.setUserImage(Utility.uploadFile(user.getImage()));
			} catch (Exception ex) {
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ex.printStackTrace(new PrintWriter(os));
				Map<String, Object> json = new HashMap<>();
				json.put("status", "upload error");
				json.put("error", os.toString());
				return json;
			}
			if (user.getEmail() != null) {
				u.setEmail(user.getEmail());
			}

			se.persist(u);
			map.put("status", "done");
			return map;
		} catch (Exception e) {
			se.getTransaction().rollback();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(bos));
			return Utility.constructJSONGetList(bos.toString(), null);
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}

	@RequestMapping("/doValidate")
	public @ResponseBody Map<String, Object> validateCode(User user, @RequestBody(required = false) User userBody) {
		EntityManager se = entityManagerFactory.createEntityManager();
		Map<String, Object> map = new HashMap<>();
		try {
			if (userBody != null) {
				user = userBody;
			}

			se.getTransaction().begin();
			Query q = se.createQuery("from User where phone=?");
			q.setParameter(1, user.getPhone());
			List<User> use = q.getResultList();
			boolean validate = false;
			User theUser = null;
			for (User u : use) {
				if (u.getActivationCode().equals(user.getActivationCode())) {
					Query q1 = se.createQuery("update User set isActive=true where phone=?");
					q1.setParameter(1, user.getPhone());
					int modification = q1.executeUpdate();
					se.getTransaction().commit();
					validate = true;
					theUser = u;
				}
			}
			if (validate) {
				User u = theUser;
				return Utility.constructJSON("done", u.getId(), u.getName(), u.getEmail(), u.getPhone(),
						u.getPassword(), u.getActivationCode(), u.getFriendCode(), u.getPoints(), u.getUserCode(),
						u.getUserImage(), u.isActive(), u.getCreatedAt(), u.getUpdatedAt(), u.getPaymentMethod());
			} else {
				map.put("status", "error");
				return map;
			}
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}
	
	@RequestMapping("/doValidateWithEmail")
	public @ResponseBody Map<String, Object> validateCodeWithEmail(User user, @RequestBody(required = false) User userBody) {
		EntityManager se = entityManagerFactory.createEntityManager();
		Map<String, Object> map = new HashMap<>();
		try {
			if (userBody != null) {
				user = userBody;
			}

			se.getTransaction().begin();
			Query q = se.createQuery("from User where email=?");
			q.setParameter(1, user.getEmail());
			List<User> use = q.getResultList();
			boolean validate = false;
			User theUser = null;
			for (User u : use) {
				if (u.getActivationCode().equals(user.getActivationCode())) {
					Query q1 = se.createQuery("update User set isActive=true where email=?");
					q1.setParameter(1, user.getEmail());
					int modification = q1.executeUpdate();
					se.getTransaction().commit();
					validate = true;
					theUser = u;
				}
			}
			if (validate) {
				User u = theUser;
				return Utility.constructJSON("done", u.getId(), u.getName(), u.getEmail(), u.getPhone(),
						u.getPassword(), u.getActivationCode(), u.getFriendCode(), u.getPoints(), u.getUserCode(),
						u.getUserImage(), u.isActive(), u.getCreatedAt(), u.getUpdatedAt(), u.getPaymentMethod());
			} else {
				map.put("status", "error");
				return map;
			}
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}

	@RequestMapping("/ValidateDriverCode")
	public @ResponseBody Map<String, Object> ValidateDriverCode(Driver driver,
			@RequestBody(required = false) Driver driverBody) {
		Map<String, Object> map = new HashMap<>();
		EntityManager se = entityManagerFactory.createEntityManager();
		try {
			if (driverBody != null) {
				driver = driverBody;
			}

			se.getTransaction().begin();
			Query q = se.createQuery("from Driver where id=?");
			q.setParameter(1, driver.getId());
			List<Driver> driv = q.getResultList();
			boolean validate = false;
			Driver theDriver = null;
			for (Driver u : driv) {
				if (u.getActivationCode().equals(driver.getActivationCode())) {

					Query q1 = se.createQuery("update Driver set isActive=true where id=?");
					q1.setParameter(1, driver.getId());
					int modification = q1.executeUpdate();
					se.getTransaction().commit();
					if (modification != 0) {
						validate = true;
						theDriver = u;
					}
				}
			}
			if (validate) {
				map.put("status", "done");
				return map;
			} else {
				map.put("status", "error not the same");
				return map;
			}
		} catch (Exception e) {
			se.getTransaction().rollback();
			throw e;
		} finally {
			if (se.getTransaction().isActive()) {
				se.getTransaction().commit();
			}
			se.close();
		}
	}
}
