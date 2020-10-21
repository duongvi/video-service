/*
 * 
 * Copyright 2014 Jules White
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.magnum.mobilecloud.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.magnum.mobilecloud.video.repository.*;

import java.security.Principal;
import java.util.*;

@Controller
public class MainController {
	
	/**
	 * You will need to create one or more Spring controllers to fulfill the
	 * requirements of the assignment. If you use this file, please rename it
	 * to something other than "AnEmptyController"
	 * 
	 * 
		 ________  ________  ________  ________          ___       ___  ___  ________  ___  __       
		|\   ____\|\   __  \|\   __  \|\   ___ \        |\  \     |\  \|\  \|\   ____\|\  \|\  \     
		\ \  \___|\ \  \|\  \ \  \|\  \ \  \_|\ \       \ \  \    \ \  \\\  \ \  \___|\ \  \/  /|_   
		 \ \  \  __\ \  \\\  \ \  \\\  \ \  \ \\ \       \ \  \    \ \  \\\  \ \  \    \ \   ___  \  
		  \ \  \|\  \ \  \\\  \ \  \\\  \ \  \_\\ \       \ \  \____\ \  \\\  \ \  \____\ \  \\ \  \ 
		   \ \_______\ \_______\ \_______\ \_______\       \ \_______\ \_______\ \_______\ \__\\ \__\
		    \|_______|\|_______|\|_______|\|_______|        \|_______|\|_______|\|_______|\|__| \|__|
                                                                                                                                                                                                                                                                        
	 * 
	 */
	
	@Autowired
	private VideoRepository videos;
	
	
	// GET /video
	@RequestMapping(value="/video",method=RequestMethod.GET)
	public @ResponseBody Collection <Video> getVideoList(){
		return (Collection<Video>) videos.findAll();
	}
	
	// POST /video
	@RequestMapping(value="/video", method=RequestMethod.POST)
	public @ResponseBody Video postVideo (@RequestBody Video v) {
		v.setLikes(0);
		v.setLikedBy(new HashSet<>());
		videos.save(v);
		return v;
	}
	
	// GET /video/{id}
	@RequestMapping(value="/video/{id}",method=RequestMethod.GET)
	public @ResponseBody Video getVideoByID(@PathVariable("id") long id) {
		return videos.findOne(id);
	}
	
	// POST /video/{id}/like
	@RequestMapping(value="/video/{id}/like", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity likeVideo(
			@PathVariable("id") long id, Principal p) {
		Video v = videos.findOne(id);
		String username = p.getName();
		if (v == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
		if (v.getLikedBy().contains(username)) return new ResponseEntity(HttpStatus.BAD_REQUEST);
		v.addLike(username);
		videos.save(v);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	
	// POST /video/{id}/unlike
	@RequestMapping(value="/video/{id}/unlike", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity unlikeVideo(
			@PathVariable("id") long id, Principal p) {
		Video v = videos.findOne(id);
		String username = p.getName();
		if (v == null) return new ResponseEntity(HttpStatus.NOT_FOUND);
		if (!v.getLikedBy().contains(username)) return new ResponseEntity(HttpStatus.BAD_REQUEST);
		v.removeLike(username);
		videos.save(v);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	
	// GET /video/search/findByName?title={title}
	@RequestMapping(value="video/search/findByName", method = RequestMethod.GET)
	public @ResponseBody Collection<Video> getVideoByTitle (
			@RequestParam("title") String name){
		return videos.findByName(name);
	}
	
	// GET /video/search/findByDurationLessThan?duration={duration}
	@RequestMapping(value="video/search/findByDurationLessThan", method = RequestMethod.GET)
	public @ResponseBody Collection<Video> getVideoByDuration (@RequestParam("duration") long duration){
		return videos.findByDurationLessThan(duration);
	}
}
