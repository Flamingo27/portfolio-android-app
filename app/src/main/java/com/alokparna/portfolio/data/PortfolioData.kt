package com.alokparna.portfolio.data

data class Portfolio(
    val name: String,
    val title: String,
    val contact: Contact,
    val education: List<Education>,
    val experience: List<Experience>,
    val projects: List<Project>,
    val skills: List<SkillCategory>,
    val achievements: List<Achievement>,
    val publications: List<Publication>,
    val personalInfo: PersonalInfo
)

data class Contact(
    val location: String,
    val phone: String,
    val email: String,
    val github: String,
    val linkedin: String,
    val portfolioUrl: String
)

data class Education(
    val institution: String,
    val degree: String,
    val score: String,
    val duration: String,
    val logo: String,
    val location: String,
    val highlight: Boolean
)

data class Experience(
    val company: String,
    val role: String,
    val duration: String,
    val description: String,
    val highlights: List<String>,
    val links: Map<String, String>,
    val color: List<String>,
    val logo: String,
    val location: String
)

data class Project(
    val title: String,
    val subtitle: String,
    val description: String,
    val tech: List<String>,
    val achievement: String,
    val links: List<ProjectLink>,
    val image: String,
    val gradient: List<String>
)

data class ProjectLink(
    val label: String,
    val url: String,
    val type: String?
)

data class SkillCategory(
    val title: String,
    val icon: String,
    val skills: List<String>,
    val color: List<String>
)

data class Achievement(
    val title: String,
    val event: String,
    val description: String,
    val icon: String,
    val color: List<String>
)

data class Publication(
    val title: String,
    val type: String,
    val icon: String,
    val description: String,
    val url: String
)

data class PersonalInfo(
    val languages: List<String>,
    val interests: List<String>
)