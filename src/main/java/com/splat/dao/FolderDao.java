package com.splat.dao;

import com.splat.model.Folder;

import java.util.List;

/**
 * Created by shambala on 21.08.17.
 */
public interface FolderDao {
    int addFolder(Folder folder, Folder parent);

    Folder getTree();
}
