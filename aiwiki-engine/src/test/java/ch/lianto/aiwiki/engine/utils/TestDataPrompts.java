package ch.lianto.aiwiki.engine.utils;

import java.util.List;

public class TestDataPrompts {
    public final String generalKnowledgePrompt = "What is the capital of Switzerland?";
    public final String domainSpecificPrompt = "Based on what rules or algorithms does the COMCON system assign business cases to employees?";
    public final List<String> domainSpecificContext = List.of(
        "LIANTO - Automatic Space Observatory System\n" +
            "System Overview\n" +
            "LIANTO is a sophisticated automatic space observatory system designed to monitor, record, and analyze celestial events in the vast expanse of space. This cutting-edge system is equipped with advanced sensors and detectors that enable it to detect suspicious space sightings and capture auspicious stellar events in real-time. LIANTO acts as the vigilant eye in the cosmos, contributing to the exploration and understanding of the universe.\n" +
            "\n" +
            "Key Features\n" +
            "1. Automated Celestial Event Detection:\n" +
            "LIANTO employs state-of-the-art sensors and algorithms to automatically detect and classify various celestial events. From mysterious space phenomena to auspicious stellar occurrences, the system ensures comprehensive coverage and accurate event identification.\n" +
            "\n" +
            "2. Real-time Monitoring:\n" +
            "The system operates in real-time, providing instant updates on detected events. Researchers, astronomers, and space enthusiasts can access a continuous stream of data, allowing them to stay informed about the latest developments in the cosmos.\n" +
            "\n" +
            "3. Central Event Bus:\n" +
            "LIANTO utilizes a centralized event bus to persistently store information about detected events. This event bus acts as a reliable and scalable data repository, ensuring that all recorded celestial events are securely stored and easily accessible for further analysis.\n" +
            "\n" +
            "4. Event Logging and Metadata:\n" +
            "Each detected event is logged with detailed metadata, including timestamp, celestial coordinates, spectral data, and any relevant contextual information. This comprehensive logging system facilitates in-depth analysis and correlation studies.\n" +
            "\n" +
            "5. Alerting and Notification System:\n" +
            "LIANTO features an intelligent alerting system that can notify designated stakeholders and experts about specific types of celestial events. This ensures a swift response to critical observations and encourages collaboration among the astronomical community.\n" +
            "\n" +
            "6. Integration with Observatories and Telescopes:\n" +
            "The system seamlessly integrates with ground-based observatories and space telescopes, enhancing its observational capabilities. LIANTO can coordinate with external instruments to provide a more comprehensive view of celestial events.\n" +
            "\n" +
            "System Architecture\n" +
            "LIANTO is built on a robust and scalable architecture, featuring:\n" +
            "\n" +
            "Sensor Array: Advanced sensors strategically positioned for optimal coverage.\n" +
            "Event Processing Engine: Responsible for real-time event detection and classification.\n" +
            "Central Event Bus: A scalable and persistent storage system for recorded events.\n" +
            "Notification Service: Facilitates timely alerts to designated recipients.\n" +
            "User Interface: Intuitive interfaces for researchers and astronomers to visualize and analyze event data.\n" +
            "Use Cases\n" +
            "Space Anomaly Detection:\n" +
            "\n" +
            "Detecting and recording unusual or suspicious space phenomena that may require further investigation.\n" +
            "Stellar Phenomenon Analysis:\n" +
            "\n" +
            "Analyzing auspicious stellar events to contribute to the understanding of cosmic phenomena.\n" +
            "Collaborative Research:\n" +
            "\n" +
            "Facilitating collaboration among astronomers, researchers, and observatories by providing a centralized platform for event sharing and analysis.\n" +
            "Education and Outreach:\n" +
            "\n" +
            "Supporting educational initiatives by providing a platform for students and educators to explore real-time celestial events.\n" +
            "Conclusion\n" +
            "LIANTO stands at the forefront of space exploration, offering an automated and intelligent solution for monitoring and recording celestial events. With its advanced capabilities, real-time updates, and seamless integration with astronomical instruments, LIANTO contributes significantly to our understanding of the mysteries that unfold in the vastness of the cosmos.\n" +
            "\n",
        "STAR_BUS - System Specification for Client Systems\n" +
            "1. COMCON - Celestial Object Risk Analysis System\n" +
            "1.1 Overview\n" +
            "COMCON is a specialized system that interfaces with the STAR_BUS event bus to perform risk analysis based on celestial events. It leverages information such as the speed, vector, and other relevant data of celestial objects to estimate potential risks and threats. The integration with STAR_BUS allows COMCON to receive real-time event data for analysis and generate risk assessments promptly.\n" +
            "\n" +
            "1.2 Key Features\n" +
            "1.2.1 Risk Estimation\n" +
            "Dynamic Analysis: Utilizes the real-time celestial event data from STAR_BUS to dynamically assess the risk associated with celestial objects.\n" +
            "\n" +
            "Parameterized Estimation: Factors in parameters such as speed, vector, and additional celestial characteristics to refine risk assessments.\n" +
            "\n" +
            "1.2.2 Alerting Mechanism\n" +
            "Automated Alerts: Triggers alerts for high-risk celestial events, ensuring that stakeholders are informed promptly.\n" +
            "\n" +
            "Integration with Notification Systems: Seamlessly integrates with external notification systems for immediate response to critical events.\n" +
            "\n" +
            "1.3 Interaction with STAR_BUS\n" +
            "1.3.1 Event Subscription\n" +
            "COMCON subscribes to relevant celestial event channels on STAR_BUS to receive real-time updates on detected events.\n" +
            "\n" +
            "1.3.2 Data Processing\n" +
            "Upon receiving event data, COMCON processes and analyzes the information, considering various risk parameters.\n" +
            "\n" +
            "1.3.3 Alert Triggering\n" +
            "If a celestial event is identified as high risk, COMCON triggers alerts and communicates with other systems for further action.\n" +
            "\n" +
            "1.4 Use Cases\n" +
            "Near-Earth Object (NEO) Monitoring:\n" +
            "\n" +
            "Assessing the risk associated with the trajectory and speed of NEOs to provide early warnings if potential threats are identified.\n" +
            "Satellite Collision Avoidance:\n" +
            "\n" +
            "Analyzing the vectors and trajectories of satellites to estimate the risk of collisions and recommend course adjustments.\n" +
            "Space Debris Management:\n" +
            "\n" +
            "Evaluating the risk posed by space debris based on its speed and trajectory, facilitating proactive measures to mitigate potential hazards.\n" +
            "2. Employee Management System\n" +
            "2.1 Overview\n" +
            "The Employee Management System is designed to collaborate with STAR_BUS and COMCON, providing human expertise in response to high-risk celestial events. It assists in identifying and assigning employees with specific skills in chemistry, physics, or other relevant domains to address potential threats or events flagged by COMCON.\n" +
            "\n" +
            "2.2 Key Features\n" +
            "2.2.1 Skill-Based Employee Matching\n" +
            "Skill Database: Maintains a database of employee skills, including expertise in chemistry, physics, astronomy, and related domains.\n" +
            "\n" +
            "Dynamic Matching: Dynamically matches employees based on the nature and requirements of the celestial event reported by COMCON.\n" +
            "\n" +
            "2.2.2 Employee Availability\n" +
            "Real-time Availability: Tracks the availability of employees with relevant skills in real-time.\n" +
            "\n" +
            "Automated Assignment: Automatically assigns available employees to address reported celestial events requiring human intervention.\n" +
            "\n" +
            "2.3 Interaction with STAR_BUS and COMCON\n" +
            "2.3.1 Event Inquiry\n" +
            "The Employee Management System interfaces with COMCON to inquire about the nature and specifics of the reported celestial event.\n" +
            "\n" +
            "2.3.2 Skill-Based Query\n" +
            "Based on the event details received from COMCON, the system queries the employee database to find individuals with matching skills.\n" +
            "\n" +
            "2.3.3 Employee Assignment\n" +
            "Once suitable candidates are identified, the system assigns the available employee with the required expertise to address the event.\n" +
            "\n" +
            "2.4 Use Cases\n" +
            "Chemical Analysis of Celestial Events:\n" +
            "\n" +
            "Assigning employees with expertise in chemistry to analyze and assess the chemical composition of celestial events flagged by COMCON.\n" +
            "Physics-Based Interventions:\n" +
            "\n" +
            "Identifying and assigning physicists to address celestial events requiring a deep understanding of physics principles.\n" +
            "Multidisciplinary Response:\n" +
            "\n" +
            "Coordinating responses that require expertise across multiple domains, such as chemistry, physics, and astronomy, for comprehensive event analysis.\n" +
            "Conclusion\n" +
            "The integration of COMCON and the Employee Management System with STAR_BUS enhances the overall capabilities of celestial event monitoring. COMCON performs real-time risk analysis, while the Employee Management System ensures a swift and effective human response to high-risk events. Together, these systems contribute to a comprehensive and proactive approach to celestial event detection and management.\n"
    );
}
