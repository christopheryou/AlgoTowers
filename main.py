import os.path,subprocess
from subprocess import STDOUT,PIPE

def execute_java(java_file):
    java_class,ext = os.path.splitext(java_file)
    cmd = ['java', java_class, 'SomeInputString']
    proc = subprocess.Popen(cmd, stdout=PIPE, stderr=STDOUT)
    stdout,stderr = proc.communicate(input='SomeInputstring')
    # print ('This was "' + stdout + '"')

execute_java('AlgoTowers.java')
