package com.photogram.fake.api.modules.usecase.impl;

import com.photogram.fake.api.modules.entities.domain.Comment;
import com.photogram.fake.api.modules.repositories.CommentsRepository;
import com.photogram.fake.api.modules.stereotypes.Usecase;
import com.photogram.fake.api.modules.usecase.ModifyComment;
import org.springframework.beans.factory.annotation.Autowired;

@Usecase
public class DefaultModifyComment implements ModifyComment {
    @Autowired
    CommentsRepository commentsRepository;

    @Override
    public Comment update(Model model) {
        return commentsRepository.update(model.getComment());
    }
}
