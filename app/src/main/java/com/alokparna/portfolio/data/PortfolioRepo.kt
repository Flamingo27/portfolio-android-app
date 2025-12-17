package com.alokparna.portfolio.data

object PortfolioRepo {
    val portfolio = Portfolio(
        name = "Alokparna Mitra",
        title = "Full-Stack Developer",
        contact = Contact(
            location = "Kolkata, WB",
            phone = "+91 96473 25707",
            email = "k.19.alokparnamitra@gmail.com",
            github = "https://github.com/Flamingo27",
            linkedin = "https://www.linkedin.com/in/alokparna-mitra/",
            portfolioUrl = "https://portfolio-alokparna.pages.dev"
        ),
        education = listOf(
            Education(
                institution = "Institute of Engineering & Management (IEM)",
                degree = "B.Tech in Computer Science & Engineering",
                score = "9.1 GPA",
                duration = "2024 – Present",
                logo = "iem_logo",
                location = "Salt Lake, Kolkata, WB",
                highlight = true
            ),
            Education(
                institution = "Burdwan Model School",
                degree = "Senior Secondary (CBSE) — PCM Stream with Computer Science",
                score = "84.4%",
                duration = "2022 – 2024",
                logo = "bms_logo",
                location = "Burdwan, WB",
                highlight = false
            ),
            Education(
                institution = "St. Paul's Academy",
                degree = "Secondary (ICSE)",
                score = "98.6%",
                duration = "2012 – 2022",
                logo = "spa_logo",
                location = "Burdwan, WB",
                highlight = true
            )
        ),
        experience = listOf(
            Experience(
                company = "Cura Horizon",
                role = "Co-Founder",
                duration = "2024–Present",
                description = "Developing an AI-integrated healthcare platform with hospital finder, emergency medicine assistance, and IoT device integration.",
                highlights = listOf(
                    "Built the core web UI, logic flow, and early-stage AI-assisted modules.",
                    "Designed and prototyped IoT-based emergency response features.",
                    "Created pitch materials, documentation, and technical explanations for presentations.",
                    "Launched the functional prototype — view the Live Demo.",
                    "Published implementation details — refer to the TechMag Report for deeper insights."
                ),
                links = mapOf(
                    "demo" to "https://cura-horizon-healthai.netlify.app/",
                    "report" to "https://drive.google.com/file/d/1jGCZQ3055R2RPwNjKay2eU8RVlV4QWUS/view?usp=drive_open"
                ),
                color = listOf("#06B6D4", "#3B82F6"),
                logo = "cura_horizon_logo",
                location = "Remote"
            ),
            Experience(
                company = "IIC, IIFR",
                role = "Lab Intern",
                duration = "2024–Present",
                description = "Prototyped healthcare-focused hardware-software solutions using embedded systems and IoT development tools.",
                highlights = listOf(
                    "Developed solutions using Raspberry Pi and Arduino.",
                    "Implemented MicroPython-based logic for IoT applications.",
                    "Built early-stage healthcare device prototypes."
                ),
                links = mapOf(),
                color = listOf("#3B82F6", "#06B6D4"),
                logo = "iic_logo",
                location = "Kolkata, WB"
            ),
            Experience(
                company = "IETE IEM Students' Forum",
                role = "Social Media Manager",
                duration = "2025–Present",
                description = "Managing digital presence and improving engagement across multiple social platforms.",
                highlights = listOf(
                    "Managed LinkedIn, Instagram, and Facebook brand accounts.",
                    "Designed content strategy to boost engagement.",
                    "Increased forum visibility and community reach."
                ),
                links = mapOf(),
                color = listOf("#06B6D4", "#14B8A6"),
                logo = "iete_logo",
                location = "Kolkata, WB"
            )
        ),
        projects = listOf(
            Project(
                title = "UNICRED",
                subtitle = "Blockchain Credential Verification System",
                description = "Developed secure credential verification portal with frontend workflows and integration pipelines using blockchain technology.",
                tech = listOf("React", "Node.js", "Kotlin", "PostgreSQL", "Solidity"),
                achievement = "Top 5 Finalist at GDG HackBuild, VIT Mumbai",
                links = listOf(
                    ProjectLink(
                        label = "Live Demo",
                        url = "https://unicred-portal.debarghaya.in",
                        type = "demo"
                    ),
                    ProjectLink(
                        label = "Project Drive Folder",
                        url = "https://drive.google.com/drive/folders/1K-i6kuuBj0G23VyYOGL7jthsgrd2gHMd",
                        type = "drive"
                    )
                ),
                image = "project_unicred",
                gradient = listOf("#06B6D4", "#3B82F6")
            ),
            Project(
                title = "Smart Irrigation System",
                subtitle = "Arduino UNO Based Automated Irrigation",
                description = "Designed a sensor-based irrigation automation system using soil moisture sensing and microcontroller logic. The system intelligently controls water flow to optimize plant hydration and reduce water waste.",
                tech = listOf("Arduino UNO", "Soil Moisture Sensor", "Embedded C", "IoT Logic"),
                achievement = "",
                links = listOf(),
                image = "project_irrigation",
                gradient = listOf("#22C55E", "#14B8A6")
            ),
            Project(
                title = "Smart Stocks",
                subtitle = "Automated Inventory Management",
                description = "Built responsive inventory automation system with product listing, search, and stock updates to reduce manual errors.",
                tech = listOf("React", "Node.js", "MySQL", "REST APIs"),
                achievement = "",
                links = listOf(),
                image = "project_stocks",
                gradient = listOf("#14B8A6", "#06B6D4")
            ),
            Project(
                title = "Assistive Healthcare Devices",
                subtitle = "Alzheimer's Glass & Accu-Pressure Glove",
                description = "Created innovative healthcare devices with audio/video assistance and accessible UI/UX using Raspberry Pi.",
                tech = listOf("Raspberry Pi", "Python", "IoT", "UI/UX"),
                achievement = "",
                links = listOf(
                    ProjectLink(
                        label = "Live Demo",
                        url = "https://dem-sim.netlify.app",
                        type = "demo"
                    )
                ),
                image = "project_assistive",
                gradient = listOf("#0891B2", "#3B82F6")
            )
        ),
        skills = listOf(
            SkillCategory(
                title = "Languages",
                icon = "Code",
                skills = listOf("JavaScript", "Python", "Java", "C/C++", "HTML/CSS"),
                color = listOf("#06B6D4", "#3B82F6")
            ),
            SkillCategory(
                title = "Frontend",
                icon = "Layout",
                skills = listOf("React", "Responsive Design", "UI/UX", "Tailwind CSS"),
                color = listOf("#3B82F6", "#06B6D4")
            ),
            SkillCategory(
                title = "Backend",
                icon = "Database",
                skills = listOf("Node.js", "REST APIs", "MySQL", "MongoDB", "PostgreSQL"),
                color = listOf("#14B8A6", "#06B6D4")
            ),
            SkillCategory(
                title = "Hardware/IoT",
                icon = "Cpu",
                skills = listOf("Arduino", "Raspberry Pi", "MicroPython", "IoT Systems"),
                color = listOf("#06B6D4", "#14B8A6")
            ),
            SkillCategory(
                title = "Version Control",
                icon = "GitBranch",
                skills = listOf("Git", "GitHub", "Collaboration"),
                color = listOf("#3B82F6", "#06B6D4")
            ),
            SkillCategory(
                title = "Blockchain",
                icon = "Globe",
                skills = listOf("Solidity", "Smart Contracts", "Web3"),
                color = listOf("#06B6D4", "#3B82F6")
            )
        ),
        achievements = listOf(
            Achievement(
                title = "Top 5 Finalist",
                event = "GDG HackBuild",
                description = "UNICRED project recognized among top finalists at VIT Mumbai hackathon",
                icon = "Trophy",
                color = listOf("#FBBF24", "#F59E0B")
            ),
            Achievement(
                title = "Winner",
                event = "Eureka Startup Pitch",
                description = "Cura Horizon won first place in competitive startup pitch competition",
                icon = "Award",
                color = listOf("#06B6D4", "#3B82F6")
            ),
            Achievement(
                title = "Winner",
                event = "IEMHEALS 2024",
                description = "Selected among 289 international teams for healthcare innovation",
                icon = "Star",
                color = listOf("#3B82F6", "#06B6D4")
            )
        ),
        publications = listOf(
            Publication(
                title = "NeuroLens: Capturing and Replaying Memories",
                type = "Research Paper",
                icon = "FileText",
                description = "Exploring innovative approaches to memory capture and replay technology.",
                url = "https://drive.google.com/file/d/1tNe8I2_Yp-L3CDPHQZU7esxdtA5HgbuM/view?usp=drive_link"
            ),
            Publication(
                title = "Empowering Creativity: The Positive Role of AI in Modern Authorship",
                type = "Research Article",
                icon = "FileText",
                description = "Analyzing AI's impact on creative writing and authorship in modern workflows.",
                url = "https://drive.google.com/file/d/1D8VFn4BecHFWOup0v6s8OD4WJqb1iANs/view?usp=drive_link"
            ),
            Publication(
                title = "Striving to be the best version of yourself: one block at a time",
                type = "Published Book (Kindle)",
                icon = "BookOpen",
                description = "Personal development guide available as a Kindle book on Amazon.",
                url = "https://www.amazon.in/dp/B0DL3M2CPW"
            )
        ),
        personalInfo = PersonalInfo(
            languages = listOf("Bengali (Native)", "English (Fluent)", "Hindi (Good)"),
            interests = listOf("Full-Stack Development", "UI/UX", "Open-source Contributions")
        )
    )
}