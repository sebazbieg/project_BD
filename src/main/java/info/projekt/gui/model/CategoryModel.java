package info.projekt.gui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CategoryModel {

		private final StringProperty categoryName;

		public CategoryModel() {
			this(null);
		}

		public CategoryModel(String categoryName) {
			this.categoryName = new SimpleStringProperty(categoryName);
		}

		public String getCategoryName() {
			return categoryName.get();
		}

		public void setCategoryName(String categoryName) {
			this.categoryName.set(categoryName);
		}

		public StringProperty categoryNameProperty() {
			return categoryName;
		}
}