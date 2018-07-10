package com.easytnt.statis.port.adapter.persistence;

import com.easytnt.share.domain.id.mark.MarkItemId;
import com.easytnt.share.domain.id.subject.SubjectId;
import com.easytnt.statis.domain.mark.ItemStatis;
import com.easytnt.statis.domain.mark.ItemStatisRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class ItemStatisJdbcRepository implements ItemStatisRepository {
    private JdbcTemplate jdbcTemplate;

    public ItemStatisJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ItemStatis> newItemStatisFor(MarkItemId itemId) {
        return null;
    }

    @Override
    public List<MarkItemId> findMarkItemIdOf(SubjectId subjectId) {
        return null;
    }
}