#------------------------------------------------------------------------------
# team Module
#   -- Keeps track of valuable team information and scorings
#------------------------------------------------------------------------------

#------------------------------------------------------------------------------
# TeamInfo Class
#   -- Stores performance information
#------------------------------------------------------------------------------
class _TeamInfo(object):
    """Used to handle information for different teams."""

    def __init__(self):
        self.matches = []
        # Things that go into TeamInfo....... numbers that you want as percentages and totals
        # The numbers are set up so the numbers set as 0 are check boxes that incriment when set to true                     
        self.noShow = 0                    
        self.hadAuto = 0                 
        self.crossAutoLine = 0
        #auto stuff
        self.autoPickedUpCube= 0
        self.telePickedUpCube = 0
	#cube pick-up
        self.climb = 0
        self.climbAssist = 0
        self.parking = 0
	#climbs
        self.humanPlayer = 0
	#misc
        self.techFouls = []
        self.fouls = []
        self.redCard = 0
        self.yellowCard = 0
        self.disabled = 0
        #stuff to count
        self.hasFoul = 0
        #does this team have a foul?     

    def get_info(self):
        self.totalFoul = sum(self.fouls)
        # you don't need to the the redCard, yellowCard, and disabled because they add up on their own
        self.totalTechFoul = sum(self.techFouls)
        # fouls are in arrays so they need to be added manually
        
        self.pNoShow = float(100*self.noShow)/float(len(self.matches)) if len(self.matches) else 0
        self.pHadAuto = float(100*self.hadAuto)/float(len(self.matches)) if len(self.matches) else 0
        self.pCrossAutoLine = float(100*self.crossAutoLine)/float(len(self.matches)) if len(self.matches) else 0
                                                      
        self.pAutoPickedUpCube = float(100*self.autoPickedUpCube)/float(len(self.matches)) if len(self.matches) else 0
        self.pTelePickedUpCube = float(100*self.telePickedUpCube)/float(len(self.matches)) if len(self.matches) else 0
                                                                        
        self.pClimb = float(100*self.climb)/float(len(self.matches)) if len(self.matches) else 0
        self.pClimbAssist = float(100*self.climbAssist)/float(len(self.matches)) if len(self.matches) else 0
        self.pParking = float(100*self.parking)/float(len(self.matches)) if len(self.matches) else 0
                                                      
        self.pRedCard = float(100*self.redCard)/float(len(self.matches)) if len(self.matches) else 0
        self.pYellowCard = float(100*self.yellowCard)/float(len(self.matches)) if len(self.matches) else 0
        self.pFoul = float(100*self.totalFoul)/float(len(self.matches)) if len(self.matches) else 0
        self.pTechFoul = float(100*self.totalTechFoul)/float(len(self.matches)) if len(self.matches) else 0

        self.pHumanPlayer = float(100*self.humanPlayer)/float(len(self.matches)) if len(self.matches) else 0                                                      
        self.pDisabled = float(100*self.disabled)/float(len(self.matches)) if len(self.matches) else 0                                            
                
    def getAttr(self, source):
        #return getattr(self, source)
        return None

#------------------------------------------------------------------------------
# TeamScores Class
#   -- stores data about a team's scores
#------------------------------------------------------------------------------
class _TeamScores(object):
    """Used to handle scoring data for different teams."""

    def __init__(self):
        self.autoCubesInSw1 = []
        self.autoCubesInScl = []
        self.autoCubesInSw2 = []
        self.autoCubesInEx =[]

        self.autoOwnGainSw1 = []
        self.autoOwnedSw1 = []
        self.autoOwnGainScl = []
        self.autoOwnedScl = []
        self.autoOwnGainSw2 = []
        self.autoOwnedSw2 = []

        self.autoNumOwnChangesSw1 = []
        self.autoNumOwnChangesScl = []
        self.autoNumOwnChangesSw2 = []

        self.teleCubesInSw1 = []
        self.teleCubesInScl = []
        self.teleCubesInSw2 = []
        self.teleCubesInEx = []

        self.teleOwnGainSw1 = []
        self.teleOwnedSw1 = []
        self.teleOwnGainScl = []
        self.teleOwnedScl = []
        self.teleOwnGainSw2 = []
        self.teleOwnedSw2 = []

        self.teleNumOwnChangesSw1 = []
        self.teleNumOwnChangesScl = []
        self.teleNumOwnChangesSw2 = []
        
        self.numCubesInVault = []

        self.activeFceTime = []
        self.activeLevTime = []
        self.activeBstTime = []

        self.cubesAtActiveFce = []
        self.cubesAtActiveLev = []
        self.cubesAtActiveBst = []

    def get_maxmin_scores(self):
        self.maxAutoNumOwnChangesSw1 = max(self.autoNumOwnChangesSw1) if len(self.autoNumOwnChangesSw1) else 0
        self.minAutoNumOwnChangesSw1 = min(self.autoNumOwnChangesSw1) if len(self.autoNumOwnChangesSw1) else 0
        self.maxAutoNumOwnChangesSw2 = max(self.autoNumOwnChangesSw2) if len(self.autoNumOwnChangesSw2) else 0
        self.minAutoNumOwnChangesSw2 = min(self.autoNumOwnChangesSw2) if len(self.autoNumOwnChangesSw2) else 0
        self.maxAutoNumOwnChangesScl = max(self.autoNumOwnChangesScl) if len(self.autoNumOwnChangesScl) else 0
        self.minAutoNumOwnChangesScl = min(self.autoNumOwnChangesScl) if len(self.autoNumOwnChangesScl) else 0
                                                              
        self.maxAutoCubesInSw1 = max(self.autoCubesInSw1) if len(self.autoCubesInSw1) else 0
        self.minAutoCubesInSw1 = min(self.autoCubesInSw1) if len(self.autoCubesInSw1) else 0
        self.maxAutoCubesInSw2 = max(self.autoCubesInSw2) if len(self.autoCubesInSw2) else 0
        self.minAutoCubesInSw2 = min(self.autoCubesInSw2) if len(self.autoCubesInSw2) else 0
        self.maxAutoCubesInScl = max(self.autoCubesInScl) if len(self.autoCubesInScl) else 0
        self.minAutoCubesInScl = min(self.autoCubesInScl) if len(self.autoCubesInScl) else 0
        self.maxAutoCubesInEx = max(self.autoCubesInEx) if len(self.autoCubesInEx) else 0
        self.minAutoCubesInEx = min(self.autoCubesInEx) if len(self.autoCubesInEx) else 0
                                                              
        self.maxAutoOwnGainSw1 = max(self.autoOwnGainSw1) if len(self.autoOwnGainSw1) else 0
        self.minAutoOwnGainSw1 = min(self.autoOwnGainSw1) if len(self.autoOwnGainSw1) else 0
        self.maxAutoOwnedSw1 = max(self.autoOwnedSw1) if len(self.autoOwnedSw1) else 0
        self.minAutoOwnedSw1 = min(self.autoOwnedSw1) if len(self.autoOwnedSw1) else 0                                                    
        self.maxAutoOwnGainSw2 = max(self.autoOwnGainSw2) if len(self.autoOwnGainSw2) else 0
        self.minAutoOwnGainSw2 = min(self.autoOwnGainSw2) if len(self.autoOwnGainSw2) else 0
        self.maxAutoOwnedSw2 = max(self.autoOwnedSw2) if len(self.autoOwnedSw2) else 0
        self.minAutoOwnedSw2 = min(self.autoOwnedSw2) if len(self.autoOwnedSw2) else 0                                                      
        self.maxAutoOwnGainScl = max(self.autoOwnGainScl) if len(self.autoOwnGainScl) else 0
        self.minAutoOwnGainScl = min(self.autoOwnGainScl) if len(self.autoOwnGainScl) else 0
        self.maxAutoOwnedScl = max(self.autoOwnedScl) if len(self.autoOwnedScl) else 0
        self.minAutoOwnedScl = min(self.autoOwnedScl) if len(self.autoOwnedScl) else 0

        self.maxTeleNumOwnChangesSw1 = max(self.teleNumOwnChangesSw1) if len(self.teleNumOwnChangesSw1) else 0
        self.minTeleNumOwnChangesSw1 = min(self.teleNumOwnChangesSw1) if len(self.teleNumOwnChangesSw1) else 0
        self.maxTeleNumOwnChangesSw2 = max(self.teleNumOwnChangesSw2) if len(self.teleNumOwnChangesSw2) else 0
        self.minTeleNumOwnChangesSw2 = min(self.teleNumOwnChangesSw2) if len(self.teleNumOwnChangesSw2) else 0
        self.maxTeleNumOwnChangesScl = max(self.teleNumOwnChangesScl) if len(self.teleNumOwnChangesScl) else 0
        self.minTeleNumOwnChangesScl = min(self.teleNumOwnChangesScl) if len(self.teleNumOwnChangesScl) else 0

        self.maxTeleCubesInSw1 = max(self.teleCubesInSw1) if len(self.teleCubesInSw1) else 0
        self.minTeleCubesInSw1 = min(self.teleCubesInSw1) if len(self.teleCubesInSw1) else 0
        self.maxTeleCubesInSw2 = max(self.teleCubesInSw2) if len(self.teleCubesInSw2) else 0
        self.minTeleCubesInSw2 = min(self.teleCubesInSw2) if len(self.teleCubesInSw2) else 0
        self.maxTeleCubesInScl = max(self.teleCubesInScl) if len(self.teleCubesInScl) else 0
        self.minTeleCubesInScl = min(self.teleCubesInScl) if len(self.teleCubesInScl) else 0
        self.maxTeleCubesInEx = max(self.teleCubesInEx) if len(self.teleCubesInEx) else 0
        self.minTeleCubesInEx = min(self.teleCubesInEx) if len(self.teleCubesInEx) else 0
        
        self.maxTeleOwnGainSw1 = max(self.teleOwnGainSw1) if len(self.teleOwnGainSw1) else 0
        self.minTeleOwnGainSw1 = min(self.teleOwnGainSw1) if len(self.teleOwnGainSw1) else 0
        self.maxTeleOwnedSw1 = max(self.teleOwnedSw1) if len(self.teleOwnedSw1) else 0
        self.minTeleOwnedSw1 = min(self.teleOwnedSw1) if len(self.teleOwnedSw1) else 0                                                    
        self.maxTeleOwnGainSw2 = max(self.teleOwnGainSw2) if len(self.teleOwnGainSw2) else 0
        self.minTeleOwnGainSw2 = min(self.teleOwnGainSw2) if len(self.teleOwnGainSw2) else 0
        self.maxTeleOwnedSw2 = max(self.teleOwnedSw2) if len(self.teleOwnedSw2) else 0
        self.minTeleOwnedSw2 = min(self.teleOwnedSw2) if len(self.teleOwnedSw2) else 0                                                      
        self.maxTeleOwnGainScl = max(self.teleOwnGainScl) if len(self.teleOwnGainScl) else 0
        self.minTeleOwnGainScl = min(self.teleOwnGainScl) if len(self.teleOwnGainScl) else 0
        self.maxTeleOwnedScl = max(self.teleOwnedScl) if len(self.teleOwnedScl) else 0
        self.minTeleOwnedScl = min(self.teleOwnedScl) if len(self.teleOwnedScl) else 0

        self.maxNumCubesInVault = max(self.numCubesInVault) if len(self.numCubesInVault) else 0
        self.minNumCubesInVault = min(self.numCubesInVault) if len(self.numCubesInVault) else 0

        self.maxActiveFceTime = max(self.activeFceTime) if len(self.activeFceTime) else 0
        self.minActiveFceTime = min(self.activeFceTime) if len(self.activeFceTime) else 0
        self.maxActiveLevTime = max(self.activeLevTime) if len(self.activeLevTime) else 0
        self.minActiveLevTime = min(self.activeLevTime) if len(self.activeLevTime) else 0
        self.maxActiveBstTime = max(self.activeBstTime) if len(self.activeBstTime) else 0
        self.minActiveBstTime = min(self.activeBstTime) if len(self.activeBstTime) else 0

        self.maxCubesAtActiveFce = max(self.cubesAtActiveFce) if len(self.cubesAtActiveFce) else 0
        self.minCubesAtActiveFce = min(self.cubesAtActiveFce) if len(self.cubesAtActiveFce) else 0
        self.maxCubesAtActiveLev = max(self.cubesAtActiveLev) if len(self.cubesAtActiveLev) else 0
        self.minCubesAtActiveLev = min(self.cubesAtActiveLev) if len(self.cubesAtActiveLev) else 0
        self.maxCubesAtActiveBst = max(self.cubesAtActiveBst) if len(self.cubesAtActiveBst) else 0
        self.minCubesAtActiveBst = min(self.cubesAtActiveBst) if len(self.cubesAtActiveBst) else 0

    def get_avgOff_scores(self, matches=1, auto=0, human=0):
        
        self.avgAutoNumOwnChangesSw1 = float(sum(self.autoNumOwnChangesSw1))/float(auto) if auto else 0
        self.avgAutoNumOwnChangesSw2 = float(sum(self.autoNumOwnChangesSw2))/float(auto) if auto else 0
        self.avgAutoNumOwnChangesScl = float(sum(self.autoNumOwnChangesScl))/float(auto) if auto else 0
        
        self.avgAutoCubesInSw1 = float(sum(self.autoCubesInSw1))/float(auto) if auto else 0
        self.avgAutoCubesInSw2 = float(sum(self.autoCubesInSw2))/float(auto) if auto else 0
        self.avgAutoCubesInScl = float(sum(self.autoCubesInScl))/float(auto) if auto else 0
        self.avgAutoCubesInEx = float(sum(self.autoCubesInEx))/float(auto) if auto else 0
      
        self.avgAutoOwnGainSw1 = sum(self.autoOwnGainSw1)/len(self.autoOwnGainSw1) if len(self.autoOwnGainSw1) else 0
        self.avgAutoOwnedSw1 = sum(self.autoOwnedSw1)/auto if auto else 0
        self.avgAutoOwnGainSw2 = sum(self.autoOwnGainSw2)/len(self.autoOwnGainSw2) if len(self.autoOwnGainSw2) else 0
        self.avgAutoOwnedSw2 = sum(self.autoOwnedSw2)/auto if auto else 0
        self.avgAutoOwnGainScl = sum(self.autoOwnGainScl)/len(self.autoOwnGainScl) if len(self.autoOwnGainScl) else 0
        self.avgAutoOwnedScl = sum(self.autoOwnedScl)/auto if auto else 0
        
        self.avgTeleNumOwnChangesSw1 = sum(self.teleNumOwnChangesSw1)/matches if matches else 0
        self.avgTeleNumOwnChangesSw2 = sum(self.teleNumOwnChangesSw2)/matches if matches else 0
        self.avgTeleNumOwnChangesScl = sum(self.teleNumOwnChangesScl)/matches if matches else 0
        
        self.avgTeleCubesInSw1 = float(sum(self.teleCubesInSw1))/float(matches) if matches else 0
        self.avgTeleCubesInSw2 = float(sum(self.teleCubesInSw2))/float(matches) if matches else 0
        self.avgTeleCubesInScl = float(sum(self.teleCubesInScl))/float(matches) if matches else 0
        self.avgTeleCubesInEx = float(sum(self.teleCubesInEx))/float(matches) if matches else 0
        
        self.avgTeleOwnGainSw1 = sum(self.teleOwnGainSw1)/len(self.teleOwnGainSw1) if len(self.teleOwnGainSw1) else 0
        self.avgTeleOwnedSw1 = sum(self.teleOwnedSw1)/matches if matches else 0
        self.avgTeleOwnGainSw2 = sum(self.teleOwnGainSw2)/len(self.teleOwnGainSw2) if len(self.teleOwnGainSw2) else 0
        self.avgTeleOwnedSw2 = sum(self.teleOwnedSw2)/matches if matches else 0
        self.avgTeleOwnGainScl = sum(self.teleOwnGainScl)/len(self.teleOwnGainScl) if len(self.teleOwnGainScl) else 0
        self.avgTeleOwnedScl = sum(self.teleOwnedScl)/matches if matches else 0
        
        self.avgNumCubesInVault = float(sum(self.numCubesInVault))/float(matches) if matches else 0
        
        self.avgActiveFceTime = sum(self.activeFceTime)/human if human else 0
        self.avgActiveLevTime = sum(self.activeLevTime)/human if human else 0
        self.avgActiveBstTime = sum(self.activeBstTime)/human if human else 0
        
        self.avgCubesAtActiveFce = sum(self.cubesAtActiveFce)/human if human else 0
        self.avgCubesAtActiveLev = sum(self.cubesAtActiveLev)/human if human else 0
        self.avgCubesAtActiveBst = sum(self.cubesAtActiveBst)/human if human else 0
    
    def getAttr(self, source):
        return getattr(self, source)
#------------------------------------------------------------------------------
# TeamPitInfo Class
#   -- stores data unrelated to performance on the field
#------------------------------------------------------------------------------
class _TeamPitInfo(object):
    """Used to handle information about a teams chassis and other
       non-performance related information."""

    def __init__(self):
      self.answer1 = ""
      self.answer2 = ""
      self.answer3 = ""
      self.answer4 = ""
      self.answer5 = ""
      self.answer6 = ""

    def getAttr(self, source):
        return getattr(self, source)    

#------------------------------------------------------------------------------
# TeamRankings class
#   -- place to store ranking lists, for viewing team ranks
#------------------------------------------------------------------------------
class TeamRankings(object):
    """Used to keep track of rankings for each team."""

    autoCubesInSw1_rank = []
    autoCubesInSw2_rank = []
    autoCubesInScl_rank = []
    autoCubesInEx_rank = []
    autoOwnGainSw1_rank = []
    autoOwnedSw1_rank = []
    autoOwnGainSw2_rank = []
    autoOwnedSw2_rank = []
    autoOwnGainScl_rank = []
    autoOwnedScl_rank = []
    teleCubesInSw1_rank = []
    teleCubesInSw2_rank = []
    teleCubesInScl_rank = []
    teleCubesInEx_rank = []
    teleOwnGainSw1_rank = []
    teleOwnedSw1_rank = []
    teleOwnGainSw2_rank = []
    teleOwnedSw2_rank = []
    teleOwnGainScl_rank = []
    teleOwnedScl_rank = []
    cubesInVault_rank = []
    cubesAtActiveFce_rank = []
    cubesAtActiveLev_rank = []
    cubesAtActiveBst_rank = []
    
    def __init__(self):
        print
        # no non-static class variables
        # team cannot track its own ranking:
            # rankings are defined by the user
            # rankings are dynamic, constantly changing to user request

    def getAttr(self, source):
        return getattr(self, source)

#------------------------------------------------------------------------------
# Team Class
#   -- stores and recalls team specific data
#------------------------------------------------------------------------------
class Team(object):
    """Store and recall data on a team from here."""

    team_list = []  # list holding all the teams currently loaded in the database
    available = []  # list holding all the teams not currently selected
    wanted = []     # list holding all the teams in our wanted list
    
    def __init__(self, num):
        self.number = num
        self.Info = _TeamInfo()
        self.Scores = _TeamScores()
        self.PitInfo = _TeamPitInfo()
        self.team_list.append(self)
        self.available.append(self)

        # a few of the final details predefined so as to satisfy predictions with null teams
        self.avgOff = 0
        self.avgDef = 0
        self.pOff = 0
        self.pDef = 0     

    def get_primary_details(self): # gets the offensive values of Team
        self.Info.get_info()
        self.Scores.get_avgOff_scores(len(self.Info.matches),
                                   self.Info.hadAuto, self.Info.humanPlayer)
        self.Scores.get_maxmin_scores()

        #weighted scores calculations
            
        self.offensiveWS = str(round(((self.Info.pParking/100.0)*1.0)+((self.Info.pCrossAutoLine/100.0)*4.0)+((self.Scores.avgNumCubesInVault/9.0)*5.0)
                                 +((self.Info.pAutoPickedUpCube/100.0)*6.0)+((self.Info.pTelePickedUpCube/100.0)*6.0)
                                 +((self.Scores.avgTeleOwnedScl/135.0)*7.0)+((self.Scores.avgTeleOwnedSw2/135.0)*7.0)
                                 +((1.0-(self.Scores.avgAutoOwnGainScl*(self.Scores.avgAutoNumOwnChangesScl/15.0)))*8.0)
                                 +((1.0-(self.Scores.avgAutoOwnGainSw2*(self.Scores.avgAutoNumOwnChangesSw2/15.0)))*8.0)
                                 +((self.Scores.avgAutoOwnedScl/135.0)*8.0)+((self.Scores.avgAutoOwnedSw2/135.0)*8.0)
                                 +((self.Info.pClimbAssist/100.0)*9.0)+((self.Info.pClimb/100.0)*10.0)
                                 +((1.0-(self.Scores.avgTeleOwnGainScl*(self.Scores.avgTeleNumOwnChangesScl/135.0)))*13.0)
                                 +((1.0-(self.Scores.avgTeleOwnGainSw2*(self.Scores.avgTeleNumOwnChangesSw2/135.0)))*13.0),2))
        
        self.defensiveWS = str(round(((self.Scores.avgTeleOwnedSw1/135.0)*7.0)+((self.Scores.avgAutoOwnedSw1/135.0)*8.0)
                                +((1.0-(self.Scores.avgAutoOwnGainSw1*(self.Scores.avgAutoNumOwnChangesSw1/15.0)))*8.0)
                                +((1.0-(self.Scores.avgTeleOwnGainSw1*(self.Scores.avgTeleNumOwnChangesSw1/135.0)))*13.0),2))
                                 
        self.negativeWS = str(round(((self.Info.pFoul/100.0)*-2.0)+((self.Info.pYellowCard/100.0)*-3.0)+((self.Info.pDisabled/100.0)*-10.0)
                                +((self.Info.pRedCard/100.0)*-10)+((self.Info.pTechFoul/100.0)*-11.0),2))
                                
        self.autoWS = str(round(((self.Info.pCrossAutoLine/100.0)*4.0)+((self.Info.pAutoPickedUpCube/100.0)*6.0)
                            +((self.Scores.avgAutoOwnGainSw1*(self.Scores.avgAutoNumOwnChangesSw1/15))*8.0)
                            +((self.Scores.avgAutoOwnGainScl*(self.Scores.avgAutoNumOwnChangesScl/15))*8.0)
                            +((self.Scores.avgAutoOwnGainSw2*(self.Scores.avgAutoNumOwnChangesSw2/15))*8.0)
                            +((self.Scores.avgAutoOwnedScl/135.0)*8.0)+((self.Scores.avgAutoOwnedSw2/135.0)*8.0)
                            +((self.Scores.avgAutoOwnedSw1/135.0)*8.0),2))
                            
        self.teleWS = str(round(((self.Info.pParking/100.0)*1.0)+((self.Scores.avgNumCubesInVault/9.0)*5.0)+((self.Info.pTelePickedUpCube/100.0)*6.0)
                            +((self.Scores.avgTeleOwnedScl/135.0)*7.0)+((self.Scores.avgTeleOwnedSw2/135.0)*7.0)
                            +((self.Scores.avgTeleOwnedSw1/135.0)*7.0)+((self.Info.pClimb/100.0)*10.0)+((self.Scores.avgTeleOwnedSw1/135.0)*7.0),2))

        self.totalWS = str(round(float(self.offensiveWS)+float(self.defensiveWS)+float(self.negativeWS), 2))

        matches = self.Info.matches
        self.numMatch = len(matches)
        self.pNoShow  = str(round(self.Info.pNoShow,2))  + "%"

        self.pHadAuto = str(round(self.Info.pHadAuto,2))  + "%"
        self.pCrossAutoLine = str(round(self.Info.pCrossAutoLine,2))  + "%"
        self.pAutoPickedUpCube = str(round(self.Info.pAutoPickedUpCube,2))  + "%"
        self.avgAutoCubesInSw1 = str(round(self.Scores.avgAutoCubesInSw1, 2))
        self.avgAutoCubesInSw2 = str(round(self.Scores.avgAutoCubesInSw2, 2))
        self.avgAutoCubesInScl = str(round(self.Scores.avgAutoCubesInScl, 2))
        self.avgAutoCubesInEx = str(round(self.Scores.avgAutoCubesInEx, 2))
        self.avgAutoOwnGainSw1 = str(round(self.Scores.avgAutoOwnGainSw1, 2))
        self.avgAutoOwnedSw1 = str(round(self.Scores.avgAutoOwnedSw1, 2))
        self.avgAutoOwnGainSw2 = str(round(self.Scores.avgAutoOwnGainSw2, 2))
        self.avgAutoOwnedSw2 = str(round(self.Scores.avgAutoOwnedSw2, 2))
        self.avgAutoOwnGainScl = str(round(self.Scores.avgAutoOwnGainScl, 2))
        self.avgAutoOwnedScl = str(round(self.Scores.avgAutoOwnedScl, 2))
        self.avgAutoNumOwnChangesSw1 = str(round(self.Scores.avgAutoNumOwnChangesSw1, 2))
        self.avgAutoNumOwnChangesSw2 = str(round(self.Scores.avgAutoNumOwnChangesSw2, 2))
        self.avgAutoNumOwnChangesScl = str(round(self.Scores.avgAutoNumOwnChangesScl, 2))

        self.pTelePickedUpCube = str(round(self.Info.pTelePickedUpCube,2))  + "%"
        self.avgTeleCubesInSw1 = str(round(self.Scores.avgTeleCubesInSw1, 2))
        self.avgTeleCubesInSw2 = str(round(self.Scores.avgTeleCubesInSw2, 2))
        self.avgTeleCubesInScl = str(round(self.Scores.avgTeleCubesInScl, 2))
        self.avgTeleCubesInEx = str(round(self.Scores.avgTeleCubesInEx, 2))
        self.avgTeleOwnGainSw1 = str(round(self.Scores.avgTeleOwnGainSw1, 2))
        self.avgTeleOwnedSw1 = str(round(self.Scores.avgTeleOwnedSw1, 2))
        self.avgTeleOwnGainSw2 = str(round(self.Scores.avgTeleOwnGainSw2, 2))
        self.avgTeleOwnedSw2 = str(round(self.Scores.avgTeleOwnedSw2, 2))
        self.avgTeleOwnGainScl = str(round(self.Scores.avgTeleOwnGainScl, 2))
        self.avgTeleOwnedScl = str(round(self.Scores.avgTeleOwnedScl, 2))
        self.avgTeleNumOwnChangesSw1 = str(round(self.Scores.avgTeleNumOwnChangesSw1, 2))
        self.avgTeleNumOwnChangesSw2 = str(round(self.Scores.avgTeleNumOwnChangesSw2, 2))
        self.avgTeleNumOwnChangesScl = str(round(self.Scores.avgTeleNumOwnChangesScl, 2))

        self.pClimb = str(round(self.Info.pClimb)) + "%"
        self.pClimbAssist = str(round(self.Info.pClimbAssist)) + "%"
        self.pParking = str(round(self.Info.pParking)) + "%"
                                   
        self.pHumanPlayer = str(round(self.Info.pHumanPlayer)) + "%"
        self.totTechFoul = str(round(self.Info.totalTechFoul))
        self.totFoul = str(round(self.Info.totalFoul))
        self.pHasFoul = str(int(100*self.Info.hasFoul)/len(matches)) + "%" if len(matches) else "0%"
        self.pFoul = str(round(self.Info.pFoul, 2)) + "%"
        self.pTechFoul = str(round(self.Info.pTechFoul, 2)) + "%"
        
        self.pYellowCard = str(round(self.Info.pYellowCard)) + "%"
        self.pRedCard = str(round(self.Info.pRedCard)) + "%"
        self.pDisabled = str(round(self.Info.pDisabled)) + "%"

        self.avgNumCubesInVault = str(round(self.Scores.avgNumCubesInVault))
        
        self.avgActiveFceTime = str(round(self.Scores.avgActiveFceTime))
        self.avgActiveLevTime = str(round(self.Scores.avgActiveLevTime))
        self.avgActiveBstTime = str(round(self.Scores.avgActiveBstTime))
        
        self.avgCubesAtActiveFce = str(round(self.Scores.avgCubesAtActiveFce))
        self.avgCubesAtActiveLev = str(round(self.Scores.avgCubesAtActiveLev))
        self.avgCubesAtActiveBst = str(round(self.Scores.avgCubesAtActiveBst))

    def getAttr(self, source):
        return getattr(self, source)
