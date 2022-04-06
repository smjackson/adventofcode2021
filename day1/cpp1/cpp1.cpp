// cpp1.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <deque>

using namespace std;

vector<int>* readFile(const char* file)
{
    ifstream input(file);
    vector<int>* pData = new vector<int>();

    string str;

    while (getline(input, str)) {
        pData->push_back(stoi(str));
    }

    delete pData;

    return pData;

}

int partOne(vector<int> &data)
{
    int last = data[0];
    int increases = 0;

    for (size_t i = 1; i < data.size(); ++i) {
        int cur = data[i];

        if (cur > last) {
            ++increases;
        }
        last = cur;
    }

    return increases;
}

int sumQueue(deque<int>& q)
{
    int sum = 0;
    for (size_t i = 0; i < q.size(); ++i) {
        sum += q[i];
    }

    return sum;
}

int partTwo(vector<int>& data, int windowSize)
{
    deque<int> window;
    int increases = 0;
    int lastSum = -1;

    for (size_t i = 0; i < data.size(); ++i) {
        window.push_back(data[i]);

        if (window.size() == windowSize) {
            int sum = sumQueue(window);

            if (lastSum > -1 && sum > lastSum) {
                ++increases;
            }
            lastSum = sum;

            window.pop_front();
        }
    }

    return increases;
}

int main(int argc, char* argv[])
{
    cout << "Day 1" << endl;
    cout << "--------" << endl;

    auto data = readFile(argv[1]);

    cout << partOne(*data) << " increases" << endl;
    cout << partTwo(*data, 3) << " sliding window increases" << endl;

    return 0;
}