package com.tild.desafio.seed;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.tild.desafio.blog.data.PostRepository;
import com.tild.desafio.blog.data.UserRepository;
import com.tild.desafio.blog.model.Post;
import com.tild.desafio.blog.model.User;

@Component
public class SeedDev implements ApplicationListener<ContextRefreshedEvent>{
	
	private UserRepository userRepository;
	private PostRepository postRepository;

	public SeedDev(UserRepository userRepository, PostRepository postRepository ) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		enterData();
		
		 String text = "<p>\n" +
                 "                        Cloud-native is an approach to building and running applications that fully exploits the\n" +
                 "                        advantages of the cloud computing delivery model. Cloud-native is about how applications are\n" +
                 "                        created and deployed, not where. While today public cloud impacts the thinking about\n" +
                 "                        infrastructure investment for virtually every industry, a cloud-like delivery model isn’t\n" +
                 "                        exclusive to public environments. It's appropriate for both public and private clouds. Most\n" +
                 "                        important is the ability to offer nearly limitless computing power, on-demand, along with\n" +
                 "                        modern data and application services for developers. When companies build and operate\n" +
                 "                        applications in a cloud-native fashion, they bring new ideas to market faster and respond\n" +
                 "                        sooner to customer demands.\n" +
                 "                    </p>";

         User joshLong = userRepository.findOne(1L);

         Post post = new Post();
         post.setTitle("What are Cloud-Native Applications?");
         post.setText(text);
         post.setUser(joshLong);

         postRepository.save(post);
		
	}
	
	public void enterData() {
		
		User user1 = new User();
		user1.setName("Josh Long");
		user1.setTwitter("@starbuxman");
		user1.setPhone("987654321");
		user1.setEmail("josh@outlook.com");
		
		User user2 = new User();
		user2.setName("Trisha Gee");
		user2.setTwitter("@trisha_gee");
		user2.setPhone("63829527");
		user2.setEmail("trisha@outlook.com");
		
		User user3 = new User();
		user3.setName("Mario Fusco");
		user3.setTwitter("@mariofusco");
		user3.setPhone("64398658");
		user3.setEmail("mario@outlook.com");
		
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		
		
	}

}
