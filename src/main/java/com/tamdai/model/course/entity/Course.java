package com.tamdai.model.course.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Film
 *
 */

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Course")
public class Course {
    
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long id;
    private Long userId;
    private String name;

    @Column(name = "description", length = 2000)
    private String description;
    
    private String price;
    private String dateCreateCourse;
    private String publicCourse;
    private String linkCourse;
    private String courseType;
    private String catagory;
    private String courseAge;
    public int showLock;
    
    @Column(name = "countPurchase", nullable = true)
    public Integer countPurchase;

//    @OneToMany(fetch = FetchType.EAGER)
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
//    private Set<CourseImage> courseImages = new HashSet<>();
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
//    private Set<UserEntity> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<CourseItem> courseItems = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<ImageCourse> imageCourses = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateCreateCourse() {
		return dateCreateCourse;
	}

	public void setDateCreateCourse(String dateCreateCourse) {
		this.dateCreateCourse = dateCreateCourse;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPublicCourse() {
        return publicCourse;
    }

    public void setPublicCourse(String publicCourse) {
        this.publicCourse = publicCourse;
    }

    public String getLinkCourse() {
        return linkCourse;
    }

    public void setLinkCourse(String linkCourse) {
        this.linkCourse = linkCourse;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public int getShowLock() {
        return showLock;
    }

    public void setShowLock(int showLock) {
        this.showLock = showLock;
    }

    public Set<CourseItem> getCourseItems() {
        return courseItems;
    }

    public void setCourseItems(Set<CourseItem> courseItems) {
        this.courseItems = courseItems;
    }

    public Set<ImageCourse> getImageCourses() {
        return imageCourses;
    }

    public void setImageCourses(Set<ImageCourse> imageCourses) {
        this.imageCourses = imageCourses;
    }
    
	public Integer getCountPurchase() {
		return countPurchase;
	}

	public void setCountPurchase(Integer countPurchase) {
		this.countPurchase = countPurchase;
	}
	
	public String getCourseAge() {
		return courseAge;
	}

	public void setCourseAge(String courseAge) {
		this.courseAge = courseAge;
	}

	public Course() {
    }
	
	

	public Course(Long id, Long userId, String name, String description, String price, String dateCreateCourse,
			String publicCourse, String linkCourse, String courseType, String catagory, String courseAge, int showLock,
			Integer countPurchase, Set<CourseItem> courseItems, Set<ImageCourse> imageCourses) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.dateCreateCourse = dateCreateCourse;
		this.publicCourse = publicCourse;
		this.linkCourse = linkCourse;
		this.courseType = courseType;
		this.catagory = catagory;
		this.courseAge = courseAge;
		this.showLock = showLock;
		this.countPurchase = countPurchase;
		this.courseItems = courseItems;
		this.imageCourses = imageCourses;
	}

	public Course(Long id, Long userId, String name, String description, String price, String dateCreateCourse,
			String publicCourse, String linkCourse, String courseType, String catagory, int showLock,
			Integer countPurchase, Set<CourseItem> courseItems, Set<ImageCourse> imageCourses) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.dateCreateCourse = dateCreateCourse;
		this.publicCourse = publicCourse;
		this.linkCourse = linkCourse;
		this.courseType = courseType;
		this.catagory = catagory;
		this.showLock = showLock;
		this.countPurchase = countPurchase;
		this.courseItems = courseItems;
		this.imageCourses = imageCourses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catagory == null) ? 0 : catagory.hashCode());
		result = prime * result + ((countPurchase == null) ? 0 : countPurchase.hashCode());
		result = prime * result + ((courseAge == null) ? 0 : courseAge.hashCode());
		result = prime * result + ((courseItems == null) ? 0 : courseItems.hashCode());
		result = prime * result + ((courseType == null) ? 0 : courseType.hashCode());
		result = prime * result + ((dateCreateCourse == null) ? 0 : dateCreateCourse.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imageCourses == null) ? 0 : imageCourses.hashCode());
		result = prime * result + ((linkCourse == null) ? 0 : linkCourse.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((publicCourse == null) ? 0 : publicCourse.hashCode());
		result = prime * result + showLock;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (catagory == null) {
			if (other.catagory != null)
				return false;
		} else if (!catagory.equals(other.catagory))
			return false;
		if (countPurchase == null) {
			if (other.countPurchase != null)
				return false;
		} else if (!countPurchase.equals(other.countPurchase))
			return false;
		if (courseAge == null) {
			if (other.courseAge != null)
				return false;
		} else if (!courseAge.equals(other.courseAge))
			return false;
		if (courseItems == null) {
			if (other.courseItems != null)
				return false;
		} else if (!courseItems.equals(other.courseItems))
			return false;
		if (courseType == null) {
			if (other.courseType != null)
				return false;
		} else if (!courseType.equals(other.courseType))
			return false;
		if (dateCreateCourse == null) {
			if (other.dateCreateCourse != null)
				return false;
		} else if (!dateCreateCourse.equals(other.dateCreateCourse))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imageCourses == null) {
			if (other.imageCourses != null)
				return false;
		} else if (!imageCourses.equals(other.imageCourses))
			return false;
		if (linkCourse == null) {
			if (other.linkCourse != null)
				return false;
		} else if (!linkCourse.equals(other.linkCourse))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (publicCourse == null) {
			if (other.publicCourse != null)
				return false;
		} else if (!publicCourse.equals(other.publicCourse))
			return false;
		if (showLock != other.showLock)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", userId=" + userId + ", name=" + name + ", description=" + description
				+ ", price=" + price + ", dateCreateCourse=" + dateCreateCourse + ", publicCourse=" + publicCourse
				+ ", linkCourse=" + linkCourse + ", courseType=" + courseType + ", catagory=" + catagory
				+ ", courseAge=" + courseAge + ", showLock=" + showLock + ", countPurchase=" + countPurchase
				+ ", courseItems=" + courseItems + ", imageCourses=" + imageCourses + "]";
	}

}
