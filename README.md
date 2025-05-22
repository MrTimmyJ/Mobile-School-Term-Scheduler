# Mobile-School-Term-Scheduler
A mobile app designed in Android Studio to schedule school terms, courses, and assignments, with notification reminders.

Author: Timothy Johnson <br>
Date: July 2023 to August 2023

## Overview

&nbsp;&nbsp;&nbsp;&nbsp;A mobile Android app built to help students organize and manage their academic terms, courses, and assessments.
Featuring data persistence through SQLite, push notifications for deadlines, and a structured navigation system for seamless user interaction.

&nbsp;&nbsp;&nbsp;&nbsp;The School Term Scheduler is an Android application that empowers students to plan their academic schedules efficiently.
The app organizes data into a hierarchy: Terms → Courses → Assessments.
Users can add, update, and delete information while receiving timely notifications about upcoming deadlines.

🧩 Features

    📆 Add, edit, and delete terms, courses, and assessments

    🧭 Intuitive navigation through hierarchical screens

    🔔 Push notifications for important course and assessment dates

    🗂️ Local storage using SQLite via Room database

    ✏️ Inline editing of existing data entries

    📱 Designed with responsive layouts for a seamless mobile experience

🔄 User Workflow

    Launch App → Navigate to the Term List

    Manage Terms → Add or modify term details (start/end dates)

    Courses & Assessments → Inside each term, add or manage related entries

    Notifications → Automatically alerted on approaching deadlines

    Data Persistence → All changes are stored locally via Room SQLite

📁 Code Structure

com.timj.c196/ <br>
├── UI/ <br>
│   ├── MainActivity.java &nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp; Launch activity and navigation <br>
│   ├── TermList.java &nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp; Displays list of all terms <br>
│   ├── TermDetails.java &nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp; Edits a specific term and shows related courses <br>
│   ├── CourseDetails.java &nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp; Edits a specific course and shows related assessments <br>
│   ├── AssessmentDetails.java &nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp; Edits a specific assessment <br>
│   ├── TermAdapter.java &nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp; Adapter for displaying the term list <br>
│   ├── CourseAdapter.java &nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp; Adapter for displaying courses <br>
│   ├── AssessmentAdapter.java &nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp; Adapter for displaying assessments <br>
│   └── MyReceiver.java &nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp; Handles scheduled notifications <br>
├── Database/ <br>
│   ├── C196Database.java &nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp; Room database configuration <br>
│   ├── C196Repository.java &nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp; Repository for handling DAO operations <br>
├── dao/ <br>
│   ├── TermDAO.java &nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp; Term-related queries <br>
│   ├── CourseDAO.java &nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp; Course-related queries <br>
│   └── AssessmentDAO.java &nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp; Assessment-related queries <br>
├── entities/ <br>
│   ├── Term.java &nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp; Term model <br>
│   ├── Course.java &nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp; Course model <br>
│   └── Assessment.java &nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp; Assessment model <br>

⚙️ How It Works

Room Database:

    C196Database.java defines and connects DAOs to entities.

    C196Repository.java abstracts the data layer, handling inserts, deletes, and queries.

Push Notifications:

    Uses POST_NOTIFICATIONS permission (Android 13+).

    Alerts users before start/end dates of terms, courses, and assessments.

🖼️ Screenshots / Visuals

![schooltermbanner](https://github.com/user-attachments/assets/31a74cac-0325-4f72-b892-4f59576ea7c0)

🧰 Technologies Used

    ☕ Java (SDK 11+)

    📱 Android Studio

    🗃️ SQLite via Room Persistence Library

    📐 XML for UI Layouts

    🔔 Android Notifications API

🚀 Getting Started

    1. Clone the repo:

      git clone https://github.com/MrTimmyJ/school-term-scheduler.git
      cd school-term-scheduler

    2. Open with Android Studio

    3. Run on emulator or device

    4. App auto-generates SQLite database on first launch

🌱 Planned Features

    📋 Weekly calendar view for due dates

    📧 Email reminders and calendar export

    🎨 Light/Dark mode toggle

    🌍 Language localization support

    ☁️ Cloud backup & sync via Firebase

🪪 License

This open-source project is available under the [MIT License](https://opensource.org/license/mit).
