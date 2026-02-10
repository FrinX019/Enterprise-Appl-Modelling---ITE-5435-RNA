package com.fp.week4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("member")
@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    // Get all members
    @GetMapping
    public Flux<Member> getAll() {
        System.out.println("Getting all members");
        return memberService.getAll();
    }

    // Get member by ID
    @GetMapping("{id}")
    public Mono<Member> getById(@PathVariable("id") final String id) {
        System.out.println("Getting member by ID: " + id);
        return memberService.getById(id);
    }

    // Create new member
    @PostMapping
    public Mono<Member> save(@RequestBody final Member member) {
        System.out.println("Creating new member: " + member.getName());
        return memberService.save(member);
    }

    // Update member
    @PutMapping("{id}")
    public Mono<Member> update(@PathVariable("id") final String id, @RequestBody final Member member) {
        System.out.println("Updating member with ID: " + id);
        return memberService.update(id, member);
    }

    // Delete member
    @DeleteMapping("{id}")
    public Mono<Void> delete(@PathVariable("id") final String id) {
        System.out.println("Deleting member with ID: " + id);
        return memberService.delete(id);
    }
}
