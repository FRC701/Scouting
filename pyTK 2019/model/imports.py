p#------------------------------------------------------------------------------
# Compiles all of the databases into one
#------------------------------------------------------------------------------
import sqlite3
import os

from team import *
from controller.windows import cteamdata
import model

conn = sqlite3.connect('C:\\Workspace_2018\\pyTK 2018\\scouting.db')
c = conn.cursor()

filePath = "C:\\Workspace_2018\\pyTK 2018\\Sqlite Database\\"


def create_table():
    global conn
    global c
    c.executescript('''
    CREATE TABLE IF NOT EXISTS CompetitionsAll( CompId TEXT, CompName TEXT,
              CompDate TEXT);
    CREATE TABLE IF NOT EXISTS MatchesAll( CompId TEXT, MatchNumber INTEGER,
              TeamNumber INTEGER, MatchPosition INTEGER,
              PRIMARY KEY(MatchNumber, TeamNumber, MatchPosition));
    CREATE TABLE IF NOT EXISTS PitDataAll( TeamNumber INTEGER PRIMARY KEY, TypeOfIntakeAndMech TEXT,
              TypeOfDriveTrain TEXT, AverageSpeed TEXT, ProgrammingLanguage TEXT, Comments TEXT,
              DriveBlindly INTEGER, Auto INTEGER, Vision INTEGER, Camera INTEGER, Other INTEGER,
              StartLevel1 INTEGER, StartLevel2 INTEGER, RobotCargo INTEGER, RobotHatch INTEGER,
              CargoShipCargo INTEGER, CargoShipHatch INTEGER, HatchInCargoShip INTEGER, CargoInCargoShip INTEGER,
              HatchInRocket INTEGER, CargoInRocket INTEGER, IntakeHatch INTEGER, IntakeCargo INTEGER,
              ReachFirstPlatform INTEGER, ReachSecondPlatform INTEGER, ReachThirdPlatform INTEGER, ScoreBottom INTEGER,
              ScoreMiddle INTEGER, ScoreTop INTEGER);
    CREATE TABLE IF NOT EXISTS StatsAll( CompId TEXT, MatchNumber INTEGER,
              TeamNumber INTEGER, MatchPosition INTEGER,
              NoShow INTEGER, StartLevel1 INTEGER, 
              StartLevel2 INTEGER, PreloadCargo INTEGER,
              PreloadHatch INTEGER, SandstormComments TEXT, 
              RocketTopCargo INTEGER, RocketTopHatch INTEGER, 
              RocketMiddleCargo INTEGER, RocketMiddleHatch INTEGER, 
              RocketBottomCargo INTEGER, RocketBottomHatch INTEGER, 
              CargoShipCargo INTEGER, CargoShipHatch INTEGER,
              CrossHubline INTEGER, EndLevel1 INTEGER,
              EndLevel2 INTEGER, EndLevel3 INTEGER, 
              EndNone INTEGER, RobotDisabled INTEGER,
              RedCard INTEGER, YellowCard INTEGER, 
              Fouls INTEGER, TechFouls INTEGER,
              PRIMARY KEY(MatchNumber, TeamNumber , MatchPosition));
    CREATE TABLE IF NOT EXISTS TeamsAll( TeamNumber INTEGER not null PRIMARY KEY, TeamName TEXT);
    CREATE TABLE IF NOT EXISTS Alliances( AllianceNum INTEGER not null PRIMARY KEY, TeamNum INTEGER);
    CREATE TABLE IF NOT EXISTS TeamInfo(TeamNum INTEGER PRIMARY KEY, NumMatch INTEGER,
              PercentNoShow REAL, OffWS REAL,
              DefWS REAL, TotalWS REAL,
              NegWS REAL,
              PercentStartLevel1 REAL, PercentStartLevel2 REAL,
              PercentPreloadC REAL, PercentPreloadH REAL, 
              AvgRocketTopC REAL, AvgRocketTopH REAL,
              AvgRocketMiddleC REAL, AvgRocketMiddleH REAL,
              AvgRocketBottomC REAL, AvgRocketBottomH REAL,
              AvgCargoShipC REAL, AvgCargoShipH REAL,
              PercentCrossHubLine REAL, PercentEndLevel1 REAL,
              PercentEndLevel2 REAL, PercentEndLevel3 REAL,
              PercentEndNone REAL, PercentRobotDisabled REAL,
              PercentRedCard REAL, PercentYellowCard REAL,
              PercentFouls REAL, PercentTechFouls REAL,
              MinRocketTopC REAL, MaxRocketTopC REAL,
              MinRocketTopH REAL, MaxRocketTopH REAL,
              MinRocketMiddleC REAL, MaxRocketMiddleC REAL,
              MinRocketMiddleH REAL, MaxRocketMiddleH REAL,
              MinRocketBottomC REAL, MaxRocketBottomC REAL,
              MinRocketBottomH REAL, MaxRocketBottomH REAL,
              MinCargoShipC REAL, MaxCargoShipC REAL,
              MinCargoShipH REAL, MaxCargoShipH REAL,
              MinFouls REAL, MaxFouls REAL,
              MinTechFouls REAL, MaxTechFouls REAL)''')

def add_data():
    model.imported = False
    global conn
    global c
    global filePath
    dirList = os.listdir(filePath)
    for filename in dirList:
        tabletconn = sqlite3.connect(filePath + filename)
        print (filename)
        tc = tabletconn.cursor()
        try:
            tc.execute('SELECT * FROM Matches')
            matches = tc.fetchall()
            c.executemany('INSERT OR REPLACE INTO MatchesAll VALUES (?, ?, ?, ?)', matches)
            model.imported = True
            print("successfully imported matches")
        except Exception as e:
            print ("exception in matches",e)
            model.imported = False

        try:
            tc.execute('SELECT * FROM Teams')
            teams = tc.fetchall()
            c.executemany('INSERT OR REPLACE INTO TeamsAll VALUES (?, ?)', teams)
            model.imported = True
            print("successfully imported teams")
        except Exception as e:
            print ("exception in teams",e)
            model.imported = False

        try:
            tc.execute('SELECT * FROM Competitions')
            comps = tc.fetchall()
            c.executemany('INSERT OR REPLACE INTO CompetitionsAll VALUES (?, ?, ?)', comps)
            model.imported = True
            print("successfully imported comps")
        except Exception as e:
            print ("exception in comps",e)
            model.imported = False

        try:
            tc.execute('SELECT * FROM PitData')
            pitData = tc.fetchall()
            c.executemany('INSERT OR REPLACE INTO PitDataAll VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', pitData)
            model.imported = True
            print("successfully imported pitdata") 
        except Exception as e:
            print ("exception in pitdata",e)
            model.imported = False

        try:
            tc.execute('SELECT * FROM Stats')
            stats = tc.fetchall()
            c.executemany('INSERT OR REPLACE INTO StatsAll VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', stats)
            model.imported = True
            print("successfully imported stats") 
        except Exception as e:
            print ("exception in stats",e)

            
        tc.close()
def add_teamInfo(team):
    global conn
    global c
    teamInfo = []
    controller = cteamdata.TeamDataController()
    teamInfo.append(team.number)
    for x, y in controller.dataLabelVals:
        try:
            teamInfo.append(float(team.getAttr(x)))
        except:
            teamInfo.append(float((team.getAttr(x)[:team.getAttr(x).find("%")])))
    for x, y in controller.maxminLabelVals:
            teamInfo.append(int(team.Scores.getAttr(x)))
    temp = []
    temp.append(tuple(teamInfo))
    c.executemany('INSERT OR REPLACE INTO TeamInfo VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', temp)
    conn.commit()
    
    
def add_matches():
    global conn
    global c
    global filePath
    dirList = os.listdir(filePath)
    for filename in dirList:
        tabletconn = sqlite3.connect(filePath + filename)
        tc = tabletconn.cursor()
        
        tc.execute('SELECT * FROM Matches')
        matches = tc.fetchall()
        c.executemany('INSERT OR REPLACE INTO MatchesAll VALUES (?, ?, ?, ?)', matches)
    
def add_teams():
    global conn
    global c
    global filePath
    dirList = os.listdir(filePath)
    for filename in dirList:
        tabletconn = sqlite3.connect(filePath + filename)
        tc = tabletconn.cursor()
        
        tc.execute('SELECT * FROM Teams')
        teams = tc.fetchall()
        c.executemany('INSERT OR REPLACE INTO TeamsAll VALUES (?, ?, ?)', teams)
    
def add_competitions():
    global conn
    global c
    global filePath
    dirList = os.listdir(filePath)
    for filename in dirList:
        tabletconn = sqlite3.connect(filePath + filename)
        tc = tabletconn.cursor()
        
        tc.execute('SELECT * FROM Competitions')
        comps = tc.fetchall()
        c.executemany('INSERT OR REPLACE INTO CompetitionsAll VALUES (?, ?, ?)', comps)

def add_pitData():
    global conn
    global c
    global filePath
    dirList = os.listdir(filePath)
    for filename in dirList:
        tabletconn = sqlite3.connect(filePath + filename)
        tc = tabletconn.cursor()
        
        tc.execute('SELECT * FROM PitData')
        pitData = tc.fetchall()
        c.executemany('INSERT OR REPLACE INTO PitDataAll VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', pitData)
def add_stats():
    global conn
    global c
    global filePath
    dirList = os.listdir(filePath)
    for filename in dirList:
        tabletconn = sqlite3.connect(filePath + filename)
        tc = tabletconn.cursor()
        
        tc.execute('SELECT * FROM Stats')
        stats = tc.fetchall()
        c.executemany('INSERT OR REPLACE INTO StatsAll VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', stats)

def clear_importData():
    model.imported = False
    model.pitImported = False
    Team.available = []
    Team.team_list = []


