package com.cbh.muonlineguildcommerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;

import com.cbh.muonlineguildcommerce.model.entity.Post;
import com.cbh.muonlineguildcommerce.model.repository.PostRepository;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class MuonlineGuildCommerceApplication implements CommandLineRunner {
	
	private final PostRepository postRepository;

	public static void main(String[] args) {
		SpringApplication.run(MuonlineGuildCommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("PostByMuServerAndEnabled");
//		for(Post obj: postRepository.findByEnabledAndMuServerId(true, 1L, PageRequest.of(0, 10)).getContent()) {
//			System.out.println("Id: " + obj.getId());
//			System.out.println("MuItemName: " + obj.getMuItem().getName());
//			System.out.println("MuServerName: " + obj.getMuServer().getName());
//		}
//
//		System.out.println("PostByMuServerAndMuItemNameAndEnabled");
//		for(Post obj: postRepository.findByEnabledAndMuServerIdAndMuItemName(true, 1L, "Wings", PageRequest.of(0, 10)).getContent()) {
//			System.out.println("Id: " + obj.getId());
//			System.out.println("MuItemName: " + obj.getMuItem().getName());
//			System.out.println("MuServerName: " + obj.getMuServer().getName());
//		}
//
//		System.out.println("PostByMuServerAndMuItemMuItemCategoryNameAndEnabled");
//		for(Post obj: postRepository.findByEnabledAndMuServerIdAndMuItemMuItemCategoryName(true, 1L, "Armors", PageRequest.of(0, 10)).getContent()) {
//			System.out.println("Id: " + obj.getId());
//			System.out.println("MuItemName: " + obj.getMuItem().getName());
//			System.out.println("MuItemCategoryName: " + obj.getMuItem().getMuItemCategory().getName());
//			System.out.println("MuServerName: " + obj.getMuServer().getName());
//		}
//
//		System.out.println("findByEnabledAndMuServerIdAndPostTypeName");
//		for(Post obj: postRepository.findByEnabledAndMuServerIdAndPostTypeName(true, 1L, "Buy", PageRequest.of(0, 10)).getContent()) {
//			System.out.println("Id: " + obj.getId());
//			System.out.println("MuItemName: " + obj.getMuItem().getName());
//			System.out.println("MuItemCategoryName: " + obj.getMuItem().getMuItemCategory().getName());
//			System.out.println("MuServerName: " + obj.getMuServer().getName());
//			System.out.println("PostType: " + obj.getPostType().getName());
//		}
//
//		System.out.println("findByEnabledAndMuServerIdAndUserNickname");
//		for(Post obj: postRepository.findByEnabledAndMuServerIdAndUserNickname(true, 1L, "Guest", PageRequest.of(0, 10)).getContent()) {
//			System.out.println("Id: " + obj.getId());
//			System.out.println("MuItemName: " + obj.getMuItem().getName());
//			System.out.println("MuItemCategoryName: " + obj.getMuItem().getMuItemCategory().getName());
//			System.out.println("MuServerName: " + obj.getMuServer().getName());
//			System.out.println("User: " + obj.getUser().getNickname());
//		}
	}

}
