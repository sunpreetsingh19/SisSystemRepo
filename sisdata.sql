-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 04, 2017 at 09:49 PM
-- Server version: 5.5.24-log
-- PHP Version: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `sisdata`
--

-- --------------------------------------------------------

--
-- Table structure for table `adminlogin`
--

CREATE TABLE IF NOT EXISTS `adminlogin` (
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `adminlogin`
--

INSERT INTO `adminlogin` (`username`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE IF NOT EXISTS `courses` (
  `course_id` varchar(10) NOT NULL,
  `course_description` varchar(50) NOT NULL,
  `course_num` varchar(30) NOT NULL,
  `professor_id` varchar(30) NOT NULL,
  `term` varchar(10) NOT NULL,
  `start_date` varchar(10) NOT NULL,
  `end_date` varchar(10) NOT NULL,
  `start_time` varchar(10) NOT NULL,
  `end_time` varchar(10) NOT NULL,
  `vacancy` varchar(10) NOT NULL,
  PRIMARY KEY (`course_id`,`course_description`,`course_num`),
  KEY `term` (`term`),
  KEY `course_description` (`course_description`),
  KEY `course_description_2` (`course_description`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`course_id`, `course_description`, `course_num`, `professor_id`, `term`, `start_date`, `end_date`, `start_time`, `end_time`, `vacancy`) VALUES
('INSE', 'Six Sigma Methodolody', '6210', '32', '', '11/07/2017', '11/15/2017', '01:55', '13:55', '100'),
('INSE', 'Software Quality Assurance', '6260', '2', 'Winter', '9832', '20', '-0', '3-0', '222'),
('INSE', 'Total Quality Project Management', '6230', 'F. Mafakheri', 'Summer', '11/08/2017', '11/10/2017', '02:05 AM', '02:05 PM', '199'),
('SOEN', 'sd.mfs', '4444', '', '', '', '', '', '', '199'),
('SOEN', 'Software engg processes', '6611', '434', 'Fall', 'ksc', 'sddc', 'ksc', 'csdcl33', '33'),
('SOEN', 'Software project management', '6841', '12', 'Winter', 'jo2jo3', 'dksnd', 'kjqwsdn', 'kjsn', '122'),
('SOEN', 'SRS', '6431', '343', 'Summer', 'DV', 'DSCSDCD', 'SDC', 'CD', '2323');

-- --------------------------------------------------------

--
-- Table structure for table `course_enrollment`
--

CREATE TABLE IF NOT EXISTS `course_enrollment` (
  `student_id` varchar(10) NOT NULL,
  `course_id` varchar(10) NOT NULL,
  `course_num` varchar(10) NOT NULL,
  `term` varchar(10) NOT NULL,
  `grades` varchar(5) NOT NULL,
  `course_description` varchar(50) NOT NULL,
  PRIMARY KEY (`student_id`,`course_id`,`course_num`),
  KEY `student_id` (`student_id`),
  KEY `course_id` (`course_id`),
  KEY `course_num` (`course_num`),
  KEY `term` (`term`),
  KEY `course_description` (`course_description`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course_enrollment`
--

INSERT INTO `course_enrollment` (`student_id`, `course_id`, `course_num`, `term`, `grades`, `course_description`) VALUES
('584744', 'INSE', '6230', 'Summer', 'A-', 'Total Quality Project Management'),
('584744', 'SOEN', '6431', 'Summer', '', 'SRS'),
('584744', 'SOEN', '6611', 'Fall', '', 'Software engg processes'),
('597563', 'INSE', '6230', 'Summer', '', 'Total Quality Project Management'),
('597563', 'SOEN', '6611', 'Fall', 'A-', 'Software engg processes'),
('597563', 'SOEN', '6841', 'Winter', '', 'Software project management'),
('723082', 'INSE', '6230', 'Summer', 'B+', 'Total Quality Project Management'),
('723082', 'INSE', '6260', 'Winter', '', 'Software Quality Assurance'),
('723082', 'SOEN', '6841', 'Summer', '', 'Software project management'),
('733507', 'INSE', '6210', 'Summer', '', 'Six Sigma Methodolody');

-- --------------------------------------------------------

--
-- Table structure for table `fees`
--

CREATE TABLE IF NOT EXISTS `fees` (
  `student_id` varchar(10) NOT NULL,
  `present_due` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fees`
--

INSERT INTO `fees` (`student_id`, `present_due`) VALUES
('162463', '8000'),
('597563', '10000');

-- --------------------------------------------------------

--
-- Table structure for table `programs`
--

CREATE TABLE IF NOT EXISTS `programs` (
  `id` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `programs`
--

INSERT INTO `programs` (`id`, `name`) VALUES
('COMP', 'Applied Computer Sciences'),
('FINC', 'Finance Management'),
('INSE', 'Information Systems Security'),
('QTSE', 'Quality Systems Engineering'),
('SOEN', 'Software Engineering');

-- --------------------------------------------------------

--
-- Table structure for table `sessions`
--

CREATE TABLE IF NOT EXISTS `sessions` (
  `year` varchar(10) NOT NULL,
  `term` varchar(10) NOT NULL,
  PRIMARY KEY (`year`,`term`),
  KEY `term` (`term`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sessions`
--

INSERT INTO `sessions` (`year`, `term`) VALUES
('2018', 'Fall'),
('2018', 'Summer'),
('2017', 'Winter');

-- --------------------------------------------------------

--
-- Table structure for table `student_accessible`
--

CREATE TABLE IF NOT EXISTS `student_accessible` (
  `studentid` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `dob` varchar(10) NOT NULL,
  `address` varchar(50) NOT NULL,
  `pincode` varchar(10) NOT NULL,
  `program` varchar(30) NOT NULL,
  `term` varchar(10) NOT NULL,
  `session` varchar(20) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(30) NOT NULL,
  `security_ques` varchar(50) NOT NULL,
  `security_ans` varchar(50) NOT NULL,
  PRIMARY KEY (`studentid`),
  KEY `program` (`program`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_accessible`
--

INSERT INTO `student_accessible` (`studentid`, `name`, `gender`, `dob`, `address`, `pincode`, `program`, `term`, `session`, `username`, `password`, `security_ques`, `security_ans`) VALUES
('162463', 'student', 'Male', '12/15/2017', 'kklmca ', '122122', 'FINC', 'Fall', '2018', 'student', '123@Qwerty', '', ''),
('584744', 'Navneet Singh', 'Male', '10/09/1992', '0kldsmfs', '2398u3', 'INSE', 'winter', '2017', 'n_chh', 'waheguru', '', ''),
('597563', 'Test Data', 'Male', '12/05/2017', 'Concordia University ', 'H3G1M8', 'SOEN', 'Summer', '2018', 'test_data', 'Qwerty@123', 'Which is your birth place?', 'Montreal'),
('723082', 'Sunpreet Singh', 'Male', '19/02/1993', '0,msdcldcndd cmzx,', 'h8p2r3', 'SOEN', '', 'Summer 2018', 'su_in', 'Waheguru@1313', '', ''),
('733507', 'Navneet Singh', 'Male', '10/02/1991', '0lwedlw', 'H8P2R3', 'INSE', 'Winter', '2018', 'n_chhabra', '145', 'What is your father''s name?', 'Manjit'),
('919451', 'Navneet Singh', 'Male', '10/04/1991', '0elkwmdlqw', 'h8p2r3', 'SOEN', 'Summer', '2018', 'n_chhabr', 'Waheguru@1313', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `student_data`
--

CREATE TABLE IF NOT EXISTS `student_data` (
  `studentid` varchar(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `dob` varchar(10) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `address` varchar(50) NOT NULL,
  `pincode` varchar(10) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `program` varchar(30) NOT NULL,
  `term` varchar(10) NOT NULL,
  `session` varchar(20) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(30) NOT NULL,
  `status` varchar(10) NOT NULL,
  `security_ques` varchar(50) NOT NULL,
  `security_ans` varchar(50) NOT NULL,
  PRIMARY KEY (`studentid`),
  UNIQUE KEY `username` (`username`),
  KEY `program` (`program`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student_payment`
--

CREATE TABLE IF NOT EXISTS `student_payment` (
  `student_id` varchar(10) NOT NULL,
  `Student_dues` varchar(10) NOT NULL,
  KEY `student_id` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `course_enrollment`
--
ALTER TABLE `course_enrollment`
  ADD CONSTRAINT `course_enrollment_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student_accessible` (`studentid`),
  ADD CONSTRAINT `course_enrollment_ibfk_3` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
  ADD CONSTRAINT `course_enrollment_ibfk_4` FOREIGN KEY (`term`) REFERENCES `courses` (`term`);

--
-- Constraints for table `student_payment`
--
ALTER TABLE `student_payment`
  ADD CONSTRAINT `student_payment_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student_accessible` (`studentid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
