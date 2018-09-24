package com.magdsoft.sindbad.ws.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.swing.text.DateFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magdsoft.sindbad.ws.forms.TimedLocation;
import com.magdsoft.sindbad.ws.model.Car;
import com.magdsoft.sindbad.ws.model.CarType;
import com.magdsoft.sindbad.ws.model.FreeRide;
import com.magdsoft.sindbad.ws.model.User;
import com.magdsoft.sindbad.ws.model.UserPlace;
import com.magdsoft.sindbad.ws.model.UserSearch;

class DistanceCalculation {
    
    /*
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2) {
        
        final int R = 6371; // Radius of the earth
        
        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
        + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        
        //	    double height = el1 - el2;
        
        //	    distance = Math.pow(distance, 2) + Math.pow(height, 2);
        
        return distance;
    }
    
    
}

@Controller
@RequestMapping("/api")
public class DistanceWebService {
    
    public static void addFreeRide(User user,EntityManager em){
        /*EntityManager em = entityManagerFactory.createEntityManager();
         User user;
         user = em.find(User.class, user.getId());
         
         if (user == null) {
         Map<String, Object> ret = new HashMap<>();
         ret.put("status", "nope");
         ret.put("error", "no-such-user");
         return ret;
         }*/
        int points = pointsForFreeRide(em);
        while(user.getPoints() != null && user.getPoints() >= points) {
            FreeRide freeRide = new FreeRide();
            freeRide.setUser_id(user);
            freeRide.setMoneyValue(moneyValueForFreeRide(em));
            freeRide.setCreatedAt(Date.from(Instant.now()));
            Date exp = Date.from(Instant.now());
            exp = Date.from(exp.toInstant().plus(7, ChronoUnit.DAYS));
            freeRide.setExpiredDate(exp);
            freeRide.setPoints(points);
            em.persist(freeRide);
            user.setPoints(user.getPoints() - points);
        }
        em.persist(user);
        
    }
    
    public static Map<Double, Car> getNearCars(EntityManager em, Map<String,Object> point) {
    	
    	
    	//em.getTransaction().begin();
        Query query = em.createQuery("from Car car"
                + " where longitude >= :long1 AND longitude <= :long2"
                + "   AND latitude  >= :lat1  AND latitude  <= :lat2");
		query.setParameter("long1",Double.parseDouble((String) point.get("longitude")) - 0.0125);
		query.setParameter("long2",Double.parseDouble((String) point.get("longitude")) + 0.0125);
		query.setParameter("lat1", Double.parseDouble((String) point.get("latitude")) - 0.0125);
		query.setParameter("lat2", Double.parseDouble((String) point.get("latitude")) + 0.0125);
		
		List<Car> results = query.getResultList();
		
		SortedMap<Double, Car> distances = new TreeMap<>();
		//List<SortedMap<Double, Car>> distances = new ArrayList<>();
		//SortedMap<Double, Car> dist = new TreeMap<>();
		for(Car oneCar : results) {
		distances.put(DistanceCalculation.distance(
		                                  oneCar.getLatitude(), Double.parseDouble((String) point.get("latitude")),
		                                  oneCar.getLongitude(),Double.parseDouble((String) point.get("longitude"))
		                                  ), oneCar);
		
		}
		return distances;
    	
    }
    /***
     * Now static, then it will be from the database
     *
     * @return Money value
     */
    public static int moneyValueForFreeRide(EntityManager em) {
        return 100;
    }
    
    /***
     * Now static, then it will be from the database
     *
     * @return Points for free ride
     */
    public static int pointsForFreeRide(EntityManager em) {
        return 10;
    }
    
    
//    public static Map<Double, Car> getNearCars(EntityManager em, TimedLocation timedLocation) {
//    	//em.getTransaction().begin();
//        Query query = em.createQuery("from Car car"
//                + " where longitude >= :long1 AND longitude <= :long2"
//                + "   AND latitude  >= :lat1  AND latitude  <= :lat2");
//		query.setParameter("long1", timedLocation.getLongitude() - 0.0125);
//		query.setParameter("long2", timedLocation.getLongitude() + 0.0125);
//		query.setParameter("lat1", timedLocation.getLatitude() - 0.0125);
//		query.setParameter("lat2", timedLocation.getLatitude() + 0.0125);
//		
//		List<Car> results = query.getResultList();
//		
//		SortedMap<Double, Car> distances = new TreeMap<>();
//		//List<SortedMap<Double, Car>> distances = new ArrayList<>();
//		//SortedMap<Double, Car> dist = new TreeMap<>();
//		for(Car oneCar : results) {
//		distances.put(DistanceCalculation.distance(
//		                                  oneCar.getLatitude(), timedLocation.getLatitude(),
//		                                  oneCar.getLongitude(), timedLocation.getLongitude()
//		                                  ), oneCar);
//		
//		}
//		return distances;
//    	
//    }
    
    
    public static int pointsForfriendCode(EntityManager em) {
        return 10;
    }
    
    @Autowired
    public EntityManagerFactory entityManagerFactory;
    
    @RequestMapping("/freeRidesForUser")
    public @ResponseBody Map<String, Object> freeRidesForUser(User user,
                                                              @RequestBody(required = false) User userBody) {
        
        if (userBody != null) {
            user = userBody;
        }
        
        EntityManager em = entityManagerFactory.createEntityManager();
        
        try {
            em.getTransaction().begin();
            
            user = em.find(User.class, user.getId());
            
            if (user == null) {
                Map<String, Object> ret = new HashMap<>();
                ret.put("status", "nope");
                ret.put("error", "no-such-user");
                return ret;
            }
            
            addFreeRide( user, em);
            // Add free rides according to points
            /*
             int points = pointsForFreeRide(em);
             while(user.getPoints() != null && user.getPoints() >= points) {
             FreeRide freeRide = new FreeRide();
             freeRide.setUser_id(user);
             freeRide.setMoneyValue(moneyValueForFreeRide(em));
             freeRide.setCreatedAt(Date.from(Instant.now()));
             Date exp = Date.from(Instant.now());
             exp = Date.from(exp.toInstant().plus(7, ChronoUnit.DAYS));
             freeRide.setExpiredDate(exp);
             freeRide.setPoints(points);
             em.persist(freeRide);
             user.setPoints(user.getPoints() - points);
             }
             em.persist(user);
             */
            // Get free rides
            
            List<Map<String, Object>> freeRides = new ArrayList<>();
            
            Query query = em.createQuery("from FreeRide freeRide where user_id.id = :u1");
            query.setParameter("u1", user.getId());
            
            List<FreeRide> data = query.getResultList();
            
            em.getTransaction().commit();
            
            DateFormatter dateFormatter = new DateFormatter(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
            //		dateFormatter.setFormat(DateFormat.getDateTimeInstance(DateFormat., timeStyle));
            
            // Serialize them into JSON
            
            for(FreeRide one : data) {
                Map<String, Object> freeRide = new HashMap<>();
                freeRide.put("id", one.getId());
                freeRide.put("money_value", one.getMoneyValue());
                try {
                    freeRide.put("expiration_date", dateFormatter.valueToString(one.getExpiredDate()));
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    freeRide.put("expiration_date", one.getExpiredDate());
                }
                freeRides.add(freeRide);
            }
            
            Map<String, Object> ret = new HashMap<>();
            ret.put("total_points", pointsForFreeRide(em));
            ret.put("user_points", user.getPoints());
            ret.put("free_rides", freeRides);
            return ret;
        } catch(Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            if (em.getTransaction().isActive()) { em.getTransaction().commit(); }
            em.close();
        }
        
    }
    
    @RequestMapping("/getCarsForLocation")
    public @ResponseBody Map<String, Object> getNearestTenCars(TimedLocation timedLocation,
                                                               @RequestBody(required = false) TimedLocation timedLocationBody) {
        if (timedLocationBody != null) {
            timedLocation = timedLocationBody;
        }
        
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Map<String,Object> point=new HashMap<>();
            point.put("latitude", timedLocation.getLatitude());
            point.put("longitude",timedLocation.getLongitude());
           // Map<Double, Car> distances = getNearCars(em, timedLocation);
            
            Map<Double, Car> distances = getNearCars(em, point);
            //		query.
            
            int i = 0;
            
            Map<String, Object> ret = new HashMap<>();
            ret.put("status", "done");
            
            List<Map<String, Object>> cars = new ArrayList<>();
            
            for(Entry<Double, Car> entry : distances.entrySet()) {
                Map<String, Object> car = new HashMap<>();
                car.put("id", entry.getValue().getId());
                car.put("distance", entry.getKey());
                car.put("latitude", entry.getValue().getLatitude());
                car.put("longitude", entry.getValue().getLongitude());
                car.put("car_type", entry.getValue().getCarType());
                cars.add(car);
                
                if (++i == 10) break;
            }
            
            ret.put("cars", cars);
            
            em.getTransaction().commit();
            
            return ret;
        } catch(Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            if (em.getTransaction().isActive()) { em.getTransaction().commit(); }
            em.close();
        }
    }
    
    @RequestMapping("/getRateForLocation")
    public @ResponseBody Map<String, Object> getRateForLocation(TimedLocation timedLocation,
                                                                @RequestBody(required = false) TimedLocation timedLocationBody) {
        if (timedLocationBody != null) {
            timedLocation = timedLocationBody;
        }
        
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("from CarType carType");
            
            List<CarType> results = query.getResultList();
            
            
            Map<String, Object> ret = new HashMap<>();
            ret.put("status", "done");
            
            List<Map<String, Object>> cars = new ArrayList<>();
            
            for(CarType carType : results) {
                Map<String, Object> car = new HashMap<>();
                car.put("id", carType.getId());
                car.put("name", carType.getName());
                car.put("km_price", carType.getKMPrice());
                car.put("minute_price", carType.getMinutePrice());
                cars.add(car);
            }
            
            ret.put("price", cars);
            
            em.getTransaction().commit();
            
            return ret;
        } catch(Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            if (em.getTransaction().isActive()) { em.getTransaction().commit(); }
            em.close();
        }
    }
    
    @RequestMapping("/getUserSearchHistoryAndPlaces")
    public @ResponseBody Map<String, Object> getUserSearchHistoryAndPlaces(User user,
                                                                           @RequestBody(required = false) User userBody) {
        if (userBody != null) {
            user = userBody;
        }
        
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("from UserPlace userPlace where user_id.id = :u1");
            query.setParameter("u1", user.getId());
            
            List<UserPlace> userPlaces = query.getResultList();
            
            query = em.createQuery("from UserSearch userSearch where user_id.id = :u1");
            query.setParameter("u1", user.getId());
            
            List<UserSearch> userSearches = query.getResultList();
            
            
            Map<String, Object> ret = new HashMap<>();
            ret.put("status", "done");
            
            List<Map<String, Object>> userPlacesAsMap = new ArrayList<>();
            List<Map<String, Object>> userSearchesAsMap = new ArrayList<>();
            
            for(UserPlace uP : userPlaces) {
                Map<String, Object> obj = new HashMap<>();
                obj.put("id", uP.getId());
                obj.put("name", uP.getName());
                obj.put("category", uP.getCategory());
                obj.put("latitude", uP.getLatitude());
                obj.put("longitude", uP.getLongitude());
                obj.put("updated_at", uP.getUpdatedAt());
                obj.put("created_at", uP.getCreatedAt());
                userPlacesAsMap.add(obj);
            }
            
            for(UserSearch uS : userSearches) {
                Map<String, Object> obj = new HashMap<>();
                obj.put("id", uS.getId());
                obj.put("search", uS.getSearch());
                obj.put("latitude", uS.getLatitude());
                obj.put("longitude", uS.getLongitude());
                obj.put("updated_at", uS.getUpdatedAt());
                obj.put("created_at", uS.getCreatedAt());
                userSearchesAsMap.add(obj);
            }
            
            //		List<UserPlace> userPlace = new ArrayList<>();
            //		List<UserSearch> userSearch = new ArrayList<>();
            //
            //		for(UserPlace place : userPlace) {
            //			Map<String, Object> car = new HashMap<>();
            //			car.put("id", place.getId());
            //			car.put("name", place.getName());
            //			car.put("latitude", place.getLatitude());
            //			car.put("longitude", place.getLongitude());
            //		}
            
            ret.put("search", userSearchesAsMap);
            ret.put("place", userPlacesAsMap);
            
            em.getTransaction().commit();
            
            return ret;
        } catch(Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        } finally {
            if (em.getTransaction().isActive()) { em.getTransaction().commit(); }
            em.close();
        }
        
    }
}
