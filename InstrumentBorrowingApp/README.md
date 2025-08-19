# Overview

The Instrument Rental App is an Android application designed to allow users to browse a list of musical instruments, view their details, and "borrow" them for a set number of months. Users can see their credit balance, which decreases as they borrow instruments. The app provides an intuitive user experience with a clean design and interactive components like buttons, spinners, and radio buttons for selecting instrument colors.

## Features

1. Browse Instruments: Users can scroll through different musical instruments (e.g., Guitar, Piano, Drum Set).

2. Instrument Details: View detailed information about each instrument, including an image, description, price, rating, and available colors.

3. Borrow Functionality: Allows users to borrow an instrument for a specified duration. Their available credits are deducted accordingly.

4. Credit System: The app tracks the user's current credit balance, which is updated after borrowing an instrument.

5. Color Selection: Users can select an available color for each instrument before borrowing.

6. Custom Styles: The app includes custom styles for buttons, headers, and body text to maintain a consistent and visually appealing design.

## Prerequisites
Android Studio: Ensure you have the latest version installed on your machine.

Kotlin: The project is developed using Kotlin, and familiarity with the language will help you understand the code.
## Project Structure
The project is structured as follows:

MainActivity: The first screen that displays the app's welcome message and a "Get Started" button. 

MainActivity2: The main browsing screen where users can view the list of instruments and their details. This activity also handles borrowing functionality.

BorrowDetailsActivity: The screen that displays when a user opts to borrow an instrument. The user can specify the duration of the borrowing and confirm the action.

Instrument.kt: A Parcelable data class representing each musical instrument with properties like name, description, price, rating, and available colorOptions.

## XML Layouts
activity_main.xml: Layout for the welcome screen.

activity_browsing.xml: Layout for the main browsing screen, showing instrument details and allowing users to navigate between instruments.

activity_borrow_details.xml: Layout for the borrowing details screen, where users can confirm the instrument borrowing and duration.


## Styles
The app utilizes custom styles to enhance the user interface

ImageStyle: Applies consistent width, height, gravity, and scale type to all ImageView elements across the app.

ButtonStyle: Provides a uniform look to all buttons with a rounded shape, background color, and padding.

HeaderTextStyle: Defines a bold and large text style for headers.

BodyTextStyle: Sets a medium-sized, readable text style for general content.
