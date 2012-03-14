package mtcls.client.event.helper;

import java.util.List;

import mtcls.client.controller.app.RootViewController;
import mtcls.client.controller.nav.NavigationController;
import mtcls.client.controller.view.ViewController;
import mtcls.client.event.TopicGroupEventHandler;
import mtcls.client.event.nav.NavigationEventHandler;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;

public class RegistrationHelper {
	public static <H extends EventHandler> void registerTopicHandlers(
												EventBus eventBus, 
												Type<H> type, 
												List<H> handlers){
		if(eventBus == null){
			return;
		}
		if(handlers == null){
			return;
		}
		for (H handler : handlers) {
			HandlerRegistration registration = eventBus.addHandler(type, handler);
			TopicGroupEventHandler topicHandler = (TopicGroupEventHandler)handler;
			topicHandler.setRegistration(registration);			
		}
	}

	public static <H extends EventHandler> void registerTopicHandler(
			EventBus eventBus, 
			Type<H> type, 
			H handler){
		if(eventBus == null){
			return;
		}
		if(handler == null){
			return;
		}
		HandlerRegistration registration = eventBus.addHandler(type, handler);
		TopicGroupEventHandler topicHandler = (TopicGroupEventHandler)handler;
		topicHandler.setRegistration(registration);
	}

	public static void registerRootNavigationHandlers(
			NavigationController navController, 
			EventBus eventBus,
			Type<NavigationEventHandler<RootViewController>> type,
			List<NavigationEventHandler<RootViewController>> handlers) {
		for (NavigationEventHandler<RootViewController> handler : handlers) {
			registerRootNavigationHandler(navController, eventBus, type, handler);
		}
		
	}
	public static void registerRootNavigationHandler(
			NavigationController navController, 
			EventBus eventBus,
			Type<NavigationEventHandler<RootViewController>> type,
			NavigationEventHandler<RootViewController> handler) {
		handler.bind(navController);
		registerTopicHandler(eventBus, type, handler);
	}
	
	public static void registerViewNavigationHandlers(
			NavigationController navController, 
			EventBus eventBus,
			Type<NavigationEventHandler<ViewController>> type,
			List<NavigationEventHandler<ViewController>> handlers) {
		for (NavigationEventHandler<ViewController> handler : handlers) {
			registerViewNavigationHandler(navController, eventBus, type, handler);
		}
		
	}
	public static void registerViewNavigationHandler(
			NavigationController navController, 
			EventBus eventBus,
			Type<NavigationEventHandler<ViewController>> type,
			NavigationEventHandler<ViewController> handler) {
		// type checking -- probably unnecessary and latent
//		if(handler != null && handler instanceof AbstractNavigationEventHandler){
//			AbstractNavigationEventHandler abstractHandler = (AbstractNavigationEventHandler)handler;
//			if(abstractHandler instanceof RootViewNavigationHandler){
//				return;
//			}
//		}
		handler.bind(navController);
		registerTopicHandler(eventBus, type, handler);
	}
	
}
