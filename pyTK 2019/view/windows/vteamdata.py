#------------------------------------------------------------------------------
# vteamdata module
#   -- contains the application for viewing the teamdata window
#------------------------------------------------------------------------------
from tkinter import *

from controller.windows import cteamdata
from view.customs import lreg
          
#------------------------------------------------------------------------------
# TeamData Class
#   -- creates a frame that holds specific data for the selected team
#------------------------------------------------------------------------------
class TeamData(Frame):
    """Class that handles running the window to view data on a team."""
    
    def graph_data(self, graphType, event=None):
        if self.graph1Used == False:
            #remove the previous graph to make room for the new one
            self.graph.destroy()
            self.labelG1.destroy()
            #get the attr value from our Team and make us a copy
            self.graphData = self.controller.get_GraphData(graphType=graphType)
            print (self.graphData)

            if self.graphData:
                self.graph = lreg.Lreg(self.graphFrame, self.graphData)
                self.graph.pack()
                self.labelG1 = Label(self.graphFrame, text=self.controller.get_GraphName(graphType))
                self.labelG1.pack()
                self.graph1Used = True
        elif self.graph1Used == True and self.graph2Used == False:
            #remove the previous graph to make room for the new one
            self.graph2.destroy()
            self.labelG2.destroy()
            #get the attr value from our Team and make us a copy
            self.graphData = self.controller.get_GraphData(graphType=graphType)
            print (self.graphData)

            if self.graphData:
                self.graph2 = lreg.Lreg(self.graphFrame2, self.graphData)
                self.graph2.pack()
                self.labelG2 = Label(self.graphFrame2, text=self.controller.get_GraphName(graphType))
                self.labelG2.pack()
                self.graph2Used = True

    def clear_graph_data(self, event=None):
        self.graph.destroy()
        self.graph2.destroy()
        self.graph1Used = False
        self.graph2Used = False
        self.labelG1.destroy()
        self.labelG2.destroy()
        
        
    def show(self):   
        if self.shown == False:
             
            #make the frame to show data to
            self.pitDataFrame = Frame(self, relief=SUNKEN,bd=1)
            self.pitDataFrame.pack(side=BOTTOM,pady=10)

            #
            self.scrollbar3 = Scrollbar(self.pitDataFrame)
            self.scrollbar3.pack(side=RIGHT, fill=Y)
            self.teamPitInfo =Listbox(self.pitDataFrame,height=10, width=140,
                                      yscrollcommand=self.scrollbar3.set)
            for x, y in self.controller.pitDataStrings:
                self.labelVar = str(y) + str(self.controller.data.PitInfo.getAttr(x))
                self.teamPitInfo.insert(END, self.labelVar)
            self.teamPitInfo.pack(side=RIGHT, fill=Y)
            self.scrollbar3.config(command=self.teamPitInfo.yview)

            
            #make the frame to show data to
            self.dataFrame = Frame(self, relief=SUNKEN,bd=1)
            self.dataFrame.pack(side=BOTTOM,pady=10)

            #make the listbox and scroller containing maxmin values
            self.scrollbar2 = Scrollbar(self.dataFrame)
            self.scrollbar2.pack(side=RIGHT,fill=Y)
            self.teamScores = Listbox(self.dataFrame,height=30,width=70,
                                      yscrollcommand=self.scrollbar2.set)
            for x, y in self.controller.maxminLabelVals:
                self.labelVar = str(y) + str(self.controller.data.Scores.getAttr(x))
                self.teamScores.insert(END, self.labelVar)
            self.teamScores.pack(side=RIGHT,fill=Y)
            self.scrollbar2.config(command=self.teamScores.yview)

            #make the listbox and scroller containing information values
            self.scrollbar = Scrollbar(self.dataFrame)
            self.scrollbar.pack(side=RIGHT,fill=Y)
            self.teamData = Listbox(self.dataFrame,height=30,width=70,
                                    yscrollcommand=self.scrollbar.set)
            for x, y in self.controller.dataLabelVals:
                self.labelVar = str(y) + str(self.controller.data.getAttr(x))
                self.teamData.insert(END, self.labelVar)
            self.teamData.pack(side=RIGHT,fill=Y)
            self.teamData.bind("<Double-Button-1>",lambda event: self.graph_data(graphType=self.teamData.curselection()))
            self.teamData.bind("<Return>",lambda event: self.graph_data(graphType=self.teamData.curselection()))
            
            self.scrollbar.config(command=self.teamData.yview)

            # make the frame to show the team pic on
            self.photoFrame = Frame(self,relief=RAISED,bd=1)
            self.photoFrame.pack(side=RIGHT,padx=20,pady=10, anchor = NE)

            # make the photo
            self.photoFile = self.controller.get_PhotoImage()
            self.teamPic = Label(self.photoFrame,image=self.photoFile,width=256,height=192)
            self.teamPic.pack()

            # make the frame to show the graph on
            self.graphFrame = Frame(self,relief=RAISED,bd=1)
            self.graphFrame.pack(side=LEFT,padx=20,pady=10, anchor = NW)


            # make an empty graph canvas to satisfy the frame
            self.graph = Canvas(self.graphFrame,width=256,height=192)
            self.graph.pack()
            self.shown = True

             # make the frame to show the graph on
            self.graphFrame2 = Frame(self,relief=RAISED,bd=1)
            self.graphFrame2.pack(side=TOP,padx=20,pady=10, anchor = N)

            # make an empty graph canvas to satisfy the frame
            self.graph2 = Canvas(self.graphFrame2,width=256,height=192)
            self.graph2.pack()
            self.shown = True

            self.labelG1 = Label(self.graphFrame, text="Graph 1")
            self.labelG1.pack(side=TOP,padx=5, anchor=N)

            self.labelG2 = Label(self.graphFrame2, text="Graph 2")
            self.labelG2.pack(side=TOP,padx=5, anchor=N)

            self.button = Button(self.startupFrame, text="CLEAR GRAPHS")
            self.button.config(command=self.clear_graph_data)
            self.button.pack(side=TOP,padx=5)

    def hide(self):
        if self.shown == True:
            self.dataFrame.destroy()
            self.pitDataFrame.destroy()
            self.graphFrame.destroy()
            self.graphFrame2.destroy() 
            self.photoFrame.destroy()
            self.shown = False
            
    def load(self, event=None):
        if self.controller.loadData():
            self.hide() # remove the old contents (if there are any)
            self.show() # display the new ones
            self.parent.title("TeamData: %d"%self.controller.data.number)
        
    def startup(self):
        # create the frame for team loading
        self.startupFrame = Frame(self)
        self.startupFrame.pack(side=TOP,pady=5)

        # make the team number loading widgets and map commands / bindings
        self.label = Label(self.startupFrame, text="Team # ")
        self.label.pack(side=LEFT,padx=5)
        
        self.entry = Entry(self.startupFrame, textvariable=self.teamNum, width=4)
        self.entry.pack(side=LEFT,padx=5)
        self.entry.bind("<Return>",self.load)
        self.controller.entry = self.entry
        
        self.button = Button(self.startupFrame, text="Load Data")
        self.button.config(command=self.load)
        self.button.pack(side=LEFT,padx=5)
        
    def __init__(self, parent=None, grandParent=None, controller=None, team=""):
        self.shown = False
        self.controller = controller
        self.parent = parent
        self.grandParent = grandParent
        self.graphData = None
        self.teamNum = StringVar()
        self.teamNum.set(team)
        self.graph1Used = False
        self.graph2Used = False

        self.parent.title("TeamData")
        Frame.__init__(self, parent)
        self.pack()
        self.startup()
        if self.teamNum.get():
            self.load()
