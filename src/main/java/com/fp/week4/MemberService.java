package com.fp.week4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepo;

    // Get all members
    public Flux<Member> getAll() {
        return memberRepo.findAll().switchIfEmpty(Flux.empty());
    }

    // Get member by ID
    public Mono<Member> getById(final String id) {
        return memberRepo.findById(id);
    }

    // Update member
    public Mono<Member> update(final String id, final Member member) {
        return memberRepo.findById(id)
                .flatMap(existingMember -> {
                    member.setId(id);
                    return memberRepo.save(member);
                });
    }

    // Save new member
    public Mono<Member> save(final Member member) {
        return memberRepo.save(member);
    }

    // Delete member
    public Mono<Void> delete(final String id) {
        return memberRepo.deleteById(id);
    }
}
