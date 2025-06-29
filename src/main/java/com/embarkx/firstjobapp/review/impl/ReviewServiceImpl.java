package com.embarkx.firstjobapp.review.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.embarkx.firstjobapp.company.Company;
import com.embarkx.firstjobapp.company.CompanyService;
import com.embarkx.firstjobapp.review.Review;
import com.embarkx.firstjobapp.review.ReviewRepository;
import com.embarkx.firstjobapp.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public void addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
        } else {
            throw new IllegalArgumentException("Company with ID " + companyId + " does not exist.");
        }
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        for (Review review : reviews) {
            if (review.getId().equals(reviewId)) {
                return review;
            }
        }
        throw new IllegalArgumentException("Review with ID " + reviewId + " does not exist for company with ID " + companyId + ".");
    }

    @Override
    public void updateReview(Long companyId, Long reviewId, Review review) throws IllegalArgumentException {
        Review existingReview = getReviewById(companyId, reviewId);
        if (existingReview != null) {
            existingReview.setTitle(review.getTitle());
            existingReview.setDescription(review.getDescription());
            existingReview.setRating(review.getRating());
            reviewRepository.save(existingReview);
        } else {
            throw new IllegalArgumentException("Review with ID " + reviewId + " does not exist for company with ID " + companyId + ".");
        }
    }

    @Override
    public void deleteReview(Long companyId, Long reviewId) {
        Review existingReview = getReviewById(companyId, reviewId);
        if (existingReview != null) {
            reviewRepository.delete(existingReview);
        } else {
            throw new IllegalArgumentException("Review with ID " + reviewId + " does not exist for company with ID " + companyId + ".");
        }
    }

    

}
