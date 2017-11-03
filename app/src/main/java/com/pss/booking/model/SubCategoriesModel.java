package com.pss.booking.model;

import java.util.List;

/**
 * Created by Manu on 12/12/2016.
 */
public class SubCategoriesModel {


    /**
     * success : 1
     * categories : [{"id":"6","sub_category":"room","category_id":"1"},{"id":"7","sub_category":"private room","category_id":"1"},{"id":"8","sub_category":"house","category_id":"1"},{"id":"9","sub_category":"apartmaint","category_id":"1"},{"id":"10","sub_category":"car camping","category_id":"1"},{"id":"11","sub_category":"bed and breakfast","category_id":"1"},{"id":"12","sub_category":"guesthouses","category_id":"1"},{"id":"13","sub_category":"villas","category_id":"1"},{"id":"14","sub_category":"vacationns rentals","category_id":"1"}]
     */

    private int success;
    /**
     * id : 6
     * sub_category : room
     * category_id : 1
     */

    private List<CategoriesEntity> categories;

    public void setSuccess(int success) {
        this.success = success;
    }

    public void setCategories(List<CategoriesEntity> categories) {
        this.categories = categories;
    }

    public int getSuccess() {
        return success;
    }

    public List<CategoriesEntity> getCategories() {
        return categories;
    }

    public static class CategoriesEntity {
        private String id;
        private String sub_category;
        private String category_id;

        public void setId(String id) {
            this.id = id;
        }

        public void setSub_category(String sub_category) {
            this.sub_category = sub_category;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getId() {
            return id;
        }

        public String getSub_category() {
            return sub_category;
        }

        public String getCategory_id() {
            return category_id;
        }
    }
}
