# CS5132_PA2 Efficient Feedback and Enquiry System

Every year, the NEA receives countless reports, feedback and enquiries from the general public. These reports may span a number of different categories, which in turn may be investigated and ratified by different task forces and branches of the NEA and relevant authorities. Each of these groups will only look into certain types of cases, and hence generation of a list of reports by their priority is imperative. Some of these reports are heavily time sensitive, due to the danger that may pose, and hence must be tackled as quickly as possible by the agency. 

For our PA2, we decided to create an Efficient Feedback and Enquiry System, so that these reports may be stowed away, stored, retrieved and finally deleted in an efficient manner. Hence allowing pertinent issues to be looked at in a timely manner, whilst also decreasing downtime in terms of manual compilation of reports. 

We make use of Red Black trees which retains much of the benefits of AVL trees and other self-balancing binary trees, but due to the less restrictive balancing conditions, the average time and number of balancing operations performed is comparatively less. These trees allow for the fast merging/ deletion/ insertion of items, which will prove advantageous given the multitude of reports filed. 

There will be 2 main use cases of the application, citizens who want to submit some feedback, and NEA officers who wish to collate these.
